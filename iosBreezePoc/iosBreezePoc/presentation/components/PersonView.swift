//
//  PersonView.swift
//  iosBreezePoc
//
//  Created by Rashaun Ellison on 3/8/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct PersonView: View {
    
    private let person: SinglePerson
    
    init(
        person: SinglePerson
    ) {
        self.person = person
    }
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading){
                WebImage(url: URL(string: "https://files.breezechms.com/\(person.personDetails?.profilePicture ?? "")"))
                    .resizable()
                    .placeholder(Image(systemName: "photo")) // Placeholder Image
                    .placeholder {
                        Rectangle().foregroundColor(.white)
                    }
                    .indicator(.activity)
                    .transition(.fade(duration: 0.5))
                    .scaledToFill() // 1
                    .frame(height: 250, alignment: .center) // 2
                    .clipped() // 3
                
                VStack(alignment: .leading){
                    
                    HStack(alignment: .lastTextBaseline){
                
                        Text(person.personDetails?.name?.first ?? "")
                            .bold()
                            .frame(alignment: .trailing)
                        Text(person.personDetails?.name?.last ?? "")
                            .bold()
                            .frame(alignment: .trailing)
                    }
                    
                    Divider()
                    VStack(alignment: .leading){
                        Text("Email"
                        )
                        .foregroundColor(Color.gray)
                        .italic()
                        Spacer()
                        
                        Text(person.personDetails?.email?.address ?? "")
                            .frame(alignment: .trailing)
                    }
                    Divider()
                    VStack(alignment: .leading){
                        Text("Phone"
                        )
                        .foregroundColor(Color.gray)
                        .italic()
                        Spacer()
                        
                        Text(person.personDetails?.phone?.mobile?.number ?? "")
                            .frame(alignment: .trailing)
                    }
                    Divider()
                }
                VStack(alignment: .leading){
                    Text("Street"
                    )
                    .foregroundColor(Color.gray)
                    .italic()
                    Spacer()
                    
                    Text(person.personDetails?.address?.street ?? "")
                        .frame(alignment: .trailing)
                }
                Divider()
                VStack(alignment: .leading){
                    Text("City"
                    )
                    .foregroundColor(Color.gray)
                    .italic()
                    Spacer()
                    
                    Text(person.personDetails?.address?.city ?? "")
                        .frame(alignment: .trailing)
                }
                Divider()
                VStack(alignment: .leading){
                    Text("State"
                    )
                    .foregroundColor(Color.gray)
                    .italic()
                    Spacer()
                    
                    Text(person.personDetails?.address?.state ?? "")
                        .frame(alignment: .trailing)
                }
                Divider()
                VStack(alignment: .leading){
                    Text("Zip"
                    )
                    .foregroundColor(Color.gray)
                    .italic()
                    Spacer()
                    
                    Text(person.personDetails?.address?.zip ?? "")
                        .frame(alignment: .trailing)
                }
                
            }
            .background(Color.white)
            .padding(12)
        }
        .navigationBarTitle(Text(person.personDetails?.name?.first ?? ""), displayMode: .inline)
    }
}


//struct PersonView_Previews: PreviewProvider {
//    static var previews: some View {
//        PersonView()
//    }
//}
