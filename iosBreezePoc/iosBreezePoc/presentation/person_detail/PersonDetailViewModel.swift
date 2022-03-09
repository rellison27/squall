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
    }
    
}
