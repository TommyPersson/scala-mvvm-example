package com.tpersson.client.core

import java.util
import javafx.scene.Scene
import javafx.stage.Stage

import com.google.inject.Module
import com.tpersson.client.core.views.mainwindow.{MainWindowView, MainWindowViewModel}
import de.saxsys.mvvmfx.FluentViewLoader
import de.saxsys.mvvmfx.guice.MvvmfxGuiceApplication

class ClientApplication extends MvvmfxGuiceApplication {
  override def startMvvmfx(stage: Stage): Unit = {
    stage.setTitle("Client")

    val viewTuple = FluentViewLoader.fxmlView[MainWindowView, MainWindowViewModel](classOf[MainWindowView]).load()

    val root = viewTuple.getView

    stage.setScene(new Scene(root))
    stage.show()
  }

  override def initGuiceModules(modules: util.List[Module]): Unit = {
    modules.add(new CoreModule())
  }
}
