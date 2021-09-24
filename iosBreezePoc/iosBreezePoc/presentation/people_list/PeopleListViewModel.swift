//
//  PeopleListViewModel.swift
//  iosBreezePoc
//
//  Created by Rashaun Ellison on 9/24/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class PeopleListViewModel: ObservableObject {
    
    // Dependecies
    let populatePeopleList: PopulatePeopleList
    
    
    // State
    @Published var state: PeopleListState = PeopleListState()
    
    init(
        populatePeopleList: PopulatePeopleList
    ){
        self.populatePeopleList = populatePeopleList
        onTriggerEvent(stateEvent: PeopleListEvents.LoadPeople())
    }
    
    func onTriggerEvent(stateEvent: PeopleListEvents){
        switch stateEvent {
        case is PeopleListEvents.LoadPeople:
            loadPeople()
        case is PeopleListEvents.NextPage:
            doNothing()
        default:
            doNothing()
        }
    }
    
    private func loadPeople(){
        let currentState = (self.state.copy() as! PeopleListState)
        do {
            try populatePeopleList.execute().collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        let loading = dataState?.isLoading ?? false
                        
                        self.updateState(isLoading: loading)
                        if(data != nil){
                            self.appendPeople(people: data as! [Person])
                        }
                    }
                    
                })
        }catch {
            print("error")
        }
    }
    
    private func appendPeople(people: [Person]){
        for person in people {
            print("person id: \(person.id)")
        }
        // TODO("append people to state")
    }
    
    
    private func doNothing(){
            // does nothing
        }
    
    func updateState(
        isLoading: Bool? = nil,
        page: Int? = nil,
        query: String? = nil
    ){
        let currentState = (self.state.copy() as! PeopleListState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            page: Int32((page ?? Int(currentState.page))),
            query: query ?? currentState.query,
            people: currentState.people
        )
    }
}
