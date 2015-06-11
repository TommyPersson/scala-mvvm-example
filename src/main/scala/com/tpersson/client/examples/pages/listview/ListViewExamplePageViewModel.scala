package com.tpersson.client.examples.pages.listview

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections

import com.google.inject.Inject
import com.tpersson.client.common.utils.{ExecutionContextProvider, ViewModelBase}
import com.tpersson.client.examples.pages.common.PersonViewModel
import com.tpersson.client.examples.services.peopleservice.PeopleService

import scala.async.Async.{async, await}

class ListViewExamplePageViewModel @Inject() (
    peopleService: PeopleService,
    executionContextProvider: ExecutionContextProvider)
  extends ViewModelBase(executionContextProvider) {

  val people = FXCollections.observableArrayList[PersonViewModel]

  val selectedPerson = new SimpleObjectProperty[PersonViewModel]

  populatePeople()

  def populatePeople() = async {
    await(peopleService.fetchPeople()) match {
      case Right(result) => result.map(new PersonViewModel(_)).foreach(people.add)
      case _ => Unit
    }
  }
}
