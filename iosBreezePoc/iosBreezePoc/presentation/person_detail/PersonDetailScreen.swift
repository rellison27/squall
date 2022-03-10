//
//  PersonDetailScreen.swift
//  iosBreezePoc
//
//  Created by Rashaun Ellison on 3/8/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PersonDetailScreen: View {
    
    private let cacheModule: CacheModule
    private let getPersonModule: GetPersonModule
    private let personId: Int
    
    @ObservedObject var viewModel: PersonDetailViewModel
    
    init (
        personId: Int,
        cacheModule: CacheModule
    ) {
        self.personId = personId
        self.cacheModule = cacheModule
        self.getPersonModule = GetPersonModule(
            cacheModule: self.cacheModule
        )
        viewModel = PersonDetailViewModel(
            personId: self.personId,
            populatePersonDetail: self.getPersonModule.populatePersonDetail
        )
    }
    var body: some View {
        if viewModel.state.person != nil {
            PersonView(person: viewModel.state.person!)
        }else{
            Text("Unable to retrive the Person selected")
        }
        
    }
}
//struct PersonDetailScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        PersonDetailScreen()
//    }
//}
