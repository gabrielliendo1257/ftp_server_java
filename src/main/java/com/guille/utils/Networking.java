package com.guille.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Networking {

  private String address;
  private String publicAddres;

  public String getInetAddress() { return "127.0.0.1"; }

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

          if (address.getHostAddress().contains(".") &&
              address.getHostAddress().startsWith("192.") &&
              address.getHostAddress().length() == 13) {
            this.publicAddres = address.getHostAddress() != null
                                    ? address.getHostAddress()
                                    : "0.0.0.0";
            break;
          }

          // Filtrar solo direcciones IPv4 (sin ":")
          // if (address.getHostAddress().contains(".")) {
          // System.out.println (
          // "Interfaz: " + networkInterface.getName() +
          // " -> Dirección IPv4: " + address.getHostAddress() + " --> "
          // + " len: " + address.toString().length());
          // this.address = address.getHostAddress();
          // }
        }
      }
    } catch (SocketException e) {
      System.err.println("Error al obtener la dirección IPv4: " +
                         e.getMessage());
    }

    return this.publicAddres;
  }
}
