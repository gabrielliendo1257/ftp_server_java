package com.guille.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Networking {

  private String address;

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
    return this.address;
  }
}
