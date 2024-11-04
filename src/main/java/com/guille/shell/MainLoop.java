package com.guille.shell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainLoop {

  private Prompt prompt;

  public void mainLoop() {}
}
