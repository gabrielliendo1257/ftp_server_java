package com.guille.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Networking {

  private String address;
  private String publicAddres;

  public String getInetAddress() {

    try {
      Enumeration<NetworkInterface> interfaces =
          NetworkInterface.getNetworkInterfaces();

      while (interfaces.hasMoreElements()) {
        NetworkInterface networkInterface = interfaces.nextElement();

        // Ignorar interfaces desconectadas o que no están activas
        if (!networkInterface.isUp() || networkInterface.isLoopback()) {
          continue;
        }

        Enumeration<InetAddress> addresses =
            networkInterface.getInetAddresses();
        while (addresses.hasMoreElements()) {
          InetAddress address = addresses.nextElement();

          // Filtrar solo direcciones IPv4 (sin ":")
          if (address.getHostAddress().contains(".")) {
            System.out.println(
                "Interfaz: " + networkInterface.getName() +
                " -> Dirección IPv4: " + address.getHostAddress());
            this.address = address.getHostAddress();
            break;
          }
        }
      }
    } catch (SocketException e) {
      System.err.println("Error al obtener la dirección IPv4: " +
                         e.getMessage());
    }
    return "127.0.0.1";
  }

  public String getIpLAN() {
    try {
      Enumeration<NetworkInterface> networkInterfaces =
          NetworkInterface.getNetworkInterfaces();

      while (networkInterfaces.hasMoreElements()) {
        NetworkInterface networkInterface = networkInterfaces.nextElement();

        // Ignorar interfaces no operativas o de loopback
        if (!networkInterface.isUp() || networkInterface.isLoopback()) {
          continue;
        }

        Enumeration<InetAddress> inetAddresses =
            networkInterface.getInetAddresses();

        while (inetAddresses.hasMoreElements()) {
          InetAddress inetAddress = inetAddresses.nextElement();

          // Verificar si es una dirección IPv4
          if (!inetAddress.isLoopbackAddress() &&
              inetAddress.getHostAddress().indexOf(':') == -1) {
            System.out.println("Dirección IP de la LAN: " +
                               inetAddress.getHostAddress());
            this.publicAddres = inetAddress.getHostAddress();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return this.publicAddres;
  }

  public String getPublicIp() {

    try {
      // Crear un proceso con ProcessBuilder
      ProcessBuilder processBuilder = new ProcessBuilder();
      processBuilder.command(
          "powershell.exe"
          + "-Command"
          + "'(Get-NetIPAddress | Where-Object { "
          + "$_.InterfaceAlias -like \"*Wi-Fi*\" -and $_.AddressFamily -eq "
          + "\"IPv4\" }).IPAddress'"); // Comando
      // de
      // ejemplo

      // Iniciar el proceso
      Process process = processBuilder.start();

      // Captura y muestra la salida estándar
      InputStream inputStream = process.getInputStream();
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(inputStream));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println("Output: " + line);
      }

      // Captura y muestra la salida de error
      InputStream errorStream = process.getErrorStream();
      BufferedReader errorReader =
          new BufferedReader(new InputStreamReader(errorStream));
      String errorLine;
      while ((errorLine = errorReader.readLine()) != null) {
        System.err.println("Error: " + errorLine);
      }

      // Esperar a que el proceso termine
      int exitCode = process.waitFor();
      System.out.println("El proceso terminó con código de salida: " +
                         exitCode);

    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

    return this.publicAddres;
  }
}
