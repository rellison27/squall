//
//  PersonDetailViewModel.swift
//  iosBreezePoc
//
//  Created by Rashaun Ellison on 3/8/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

class PersonDetailViewModel: ObservableObject {
    
    // Dependencies
    private let populatePersonDetail: PopulatePersonDetail
    
    // State
    @Published var state: PersonDetailState =  PersonDetailState()
    
    init(
        personId: Int,
        populatePersonDetail: PopulatePersonDetail
    ) {
        self.populatePersonDetail = populatePersonDetail
        // TODO("Get the recipe from cache")
        onTriggerEvent(stateEvent: PersonDetailEvents.PopulatePersonDetail(personId: Int32(personId)))
    }
    
    func onTriggerEvent(stateEvent: PersonDetailEvents){
        switch stateEvent {
            case is PersonDetailEvents.PopulatePersonDetail:
                populatePersonDetail(personId: Int((stateEvent as! PersonDetailEvents.PopulatePersonDetail).personId))
        default: doNothing()
        }
    }
    
    private func populatePersonDetail(personId: Int){
                do{
                    try self.populatePersonDetail.execute(
                        personId: Int32(personId)
                    ).collectCommon(
                        coroutineScope: nil,
                        callback: { dataState in
                        if dataState != nil {
                            let data = dataState?.data
                            let loading = dataState?.isLoading ?? false
                            self.updateState(isLoading: loading)
                            
                            if(data != nil){
                                self.updateState(person: data! as SinglePerson)
                            }
                        }else{
                            print("populatePersonDetail: DataState is nil")
                        }
                    })
                }catch{
                    print("GetRecipe: ERROR: \(error)")
                }
            }
        
            
        private func doNothing(){}
        
        private func updateState(
            isLoading: Bool? = nil,
            person: SinglePerson? = nil
        ){
            let currentState = (self.state.copy() as! PersonDetailState)
            self.state = self.state.doCopy(
                isLoading: isLoading ?? currentState.isLoading,
                person: person ?? currentState.person
            )
        }
        
    
}
