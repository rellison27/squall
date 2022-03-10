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
                               Text("First Name:"
                               )
                               .foregroundColor(Color.gray)

                               Spacer()
                               
                            Text(person.personDetails?.name?.first ?? "")
                                   .frame(alignment: .trailing)
                           }
                        
                        HStack(alignment: .lastTextBaseline){
                            Text("Middle Name:"
                            )
                            .foregroundColor(Color.gray)

                            Spacer()
                            
                         Text(person.personDetails?.name?.middle ?? "")
                                .frame(alignment: .trailing)
                        }
                        
                        HStack(alignment: .lastTextBaseline){
                            Text("Nick Name:"
                            )
                            .foregroundColor(Color.gray)

                            Spacer()
                            
                         Text(person.personDetails?.name?.nick ?? "")
                                .frame(alignment: .trailing)
                        }
                        
                        HStack(alignment: .lastTextBaseline){
                            Text("Last Name:"
                            )
                            .foregroundColor(Color.gray)

                            Spacer()
                            
                         Text(person.personDetails?.name?.last ?? "")
                                .frame(alignment: .trailing)
                        }
                           
                        HStack(alignment: .lastTextBaseline){
                            Text("Email:"
                            )
                            .foregroundColor(Color.gray)

                            Spacer()
                            
                            Text(person.personDetails?.email?.address ?? "")
                                .frame(alignment: .trailing)
                        }
                        
                        HStack(alignment: .lastTextBaseline){
                            Text("Phone:"
                            )
                            .foregroundColor(Color.gray)

                            Spacer()
                            
                            Text(person.personDetails?.phone?.mobile?.number ?? "")
                                .frame(alignment: .trailing)
                        }
//                           ForEach(recipe.ingredients as Array<String>, id: \.self){ ingredient in
//                               Text(ingredient)
//                                   .padding(.top, 4)
//                           }
                        
                       }
                       .background(Color.white)
                       .padding(12)
                   }
               }
        .navigationBarTitle(Text(person.personDetails?.name?.first ?? ""), displayMode: .inline)
           }
    }


//struct PersonView_Previews: PreviewProvider {
//    static var previews: some View {
//        PersonView()
//    }
//}
