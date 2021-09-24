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
    
    init (
        networkModule: NetworkModule,
        cacheModule: CacheModule
    ){
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.populatePeopleListModule = PopulatePeopleListModule(
            networkModule: self.networkModule, cacheModule: self.cacheModule)
    }
    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
}

//struct PeopleListScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        PeopleListScreen()
//    }
//}
