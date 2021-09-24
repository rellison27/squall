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
    }
}
