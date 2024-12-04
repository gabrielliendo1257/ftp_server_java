package com.guille.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
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
    final String ipServiceUrl = "https://api.ipify.org";

    try {
      URL url = new URL(ipServiceUrl);
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("GET");

      BufferedReader reader = new BufferedReader(
          new InputStreamReader(connection.getInputStream()));
      this.publicAddres = reader.readLine();

    } catch (MalformedURLException e) {
      System.out.println("Url no valida.");
      System.exit(1);
    } catch (IOException e) {
      System.out.println("Problemas de coneccion.");
      System.exit(2);
    }

    return this.publicAddres;
  }
}
