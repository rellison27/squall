//
//  PeopleListScreen.swift
//  iosBreezePoc
//
//  Created by Rashaun Ellison on 9/24/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PeopleListScreen: View {
    
    // dependencies
    private let networkModule: NetworkModule
    private let cacheModule: CacheModule
    private let populatePeopleListModule: PopulatePeopleListModule
    
    @ObservedObject var viewModel: PeopleListViewModel
    
    init (
        networkModule: NetworkModule,
        cacheModule: CacheModule
    ){
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.populatePeopleListModule = PopulatePeopleListModule(
            networkModule: self.networkModule,
            cacheModule: self.cacheModule
        )
        self.viewModel = PeopleListViewModel(
            populatePeopleList: populatePeopleListModule.populatePeopleList
        )
    }
    
    var body: some View {
        Text("People List Screen")
    }
}

//struct PeopleListScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        PeopleListScreen()
//    }
//}
