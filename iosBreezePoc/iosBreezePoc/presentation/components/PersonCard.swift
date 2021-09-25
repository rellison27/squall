//
//  PersonCard.swift
//  iosBreezePoc
//
//  Created by Rashaun Ellison on 9/24/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct PersonCard: View {
   
    let person: Person
    
    init(person: Person){
        self.person = person
    }
    
    var body: some View {
        HStack{
        WebImage(url: URL(string: "https://files.breezechms.com/\(person.profilePicture ?? "")" ))
                .resizable()
            .interpolation(.medium)
            .placeholder{
                Image(systemName: "person.circle.fill")
                    .resizable()
                    .interpolation(.medium)
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 90, height: 60, alignment: .bottom)
                    .clipShape(/*@START_MENU_TOKEN@*/Circle()/*@END_MENU_TOKEN@*/)
                    .foregroundColor(.gray)
            } //Placeholder Image
                    .indicator(.activity)
                    .transition(.fade(duration: 0.5))
                    .aspectRatio(contentMode: .fit)
                    // .scaledToFit() // 1
            .frame(width: 90, height: 60, alignment: .topLeading) // 2
                    .clipShape(Circle())

            AdaptiveStack{
                Text(person.name?.first ?? "")
                    .font(.body)
                    .bold()
                Text(person.name?.last ?? "")
                    .font(.body)
                    .bold()
            }
            Spacer()
            VStack(alignment: .leading){
                Text(person.phone?.mobile?.number  ?? "Phone")
                    .font(.caption)
                Text(person.email?.address ?? "Email")
                    .font(.caption2)
            }
            Spacer()
            Image(systemName: "chevron.right")
                .resizable()
                .interpolation(.medium)
                .aspectRatio(contentMode: .fit)
                .frame(width: 20, height: 20, alignment: .trailing)
                .clipShape(/*@START_MENU_TOKEN@*/Circle()/*@END_MENU_TOKEN@*/)
                .foregroundColor(.gray)
        }
        .padding(.top, 6)
        .padding(.leading, 6)
        .padding(.trailing, 6)
        .padding(.bottom, 6)
    }
}

struct PersonCard_Previews: PreviewProvider {
    static let person = Person(
        id: 489203749837,
        archived: false,
        birthdate: nil,
        address: nil,
        email: nil,
        phone: nil,
        name: nil,
        profilePicture: "https://files.breezechms.com/img/profiles/upload/5b74385157c66.jpg"
    )
    static var previews: some View {
        PersonCard(person: person)
    }
}
