//
//  OnBoardingView.swift
//  iosApp
//
//  Created by Ishan on 19/07/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared


struct OnBoardingView: View {
//     let serviceApi = ServiceApi()
    
//    @State private var isActive = false
//    @State private var currentStep = 0
    
//    @State var onBoardingSteps = [Data]()
    

    @State var greet = "Loading..."
    

    
//    init(){
//        UIScrollView.appearance().bounces = false
//    }
    
    var body: some View {
        
        Text("OnBoarding!! $greet")
        
//        serviceApi.getOnBoarding { result, error in
//                if let result = result {
//                    self.onBoardingSteps = result
//                } else if let error = error {
//                    greet = "Error: \(error)"
//                }
//            }
        
        
        
        
//        if(isActive) {
////            MainAppView()
//        } else {
//
//        VStack{
//            HStack{
//                Text("NewsDx Io")
//                    .padding()
//                    .foregroundColor(.gray)
//            }
//
//            TabView(selection: $currentStep) {
//                ForEach(0..<onBoardingSteps.count) { it in
//                        VStack {
//                            Image(onBoardingSteps[it].image_url)
//                                .resizable()
//                                .frame(width: 250, height: 250)
//                            Text(onBoardingSteps[it].title)
//                                .font(.title)
//                        }
//                        .tag(it)
//                    }
//
//            }
//            .tabViewStyle(PageTabViewStyle(indexDisplayMode: .never))
//
//            HStack {
//            ForEach(0..<onBoardingSteps.count) { it in
//                    if it == currentStep{
////                        Rectangle()
////                            .frame(width: 20, height: 10)
////                            .cornerRadius(10)
////                            .foregroundColor(.purple)
//                        Circle()
//                            .frame(width: 10, height: 10)
//                            .foregroundColor(.blue)
//
//                    } else {
//                        Circle()
//                            .frame(width: 10, height: 10)
//                            .foregroundColor(.gray)
//                    }
//            }
//        }
//            .padding(.bottom, 10)
//
//            HStack{
//                Button(action: {
//                    DispatchQueue.main.asyncAfter(deadline: .now() + 1.0) {
//                        withAnimation(){
//                        self.isActive = true
//                        }
//                    }
//                }){
//                    Text("Skip")
//                        .padding()
//                        .cornerRadius(16)
//                        .foregroundColor(Color.blue)
//                }
//
//                Spacer()
//
//                currentStep < onBoardingSteps.count - 1 ? Button(action: {
//
//                }){
//                    Text("")
//                        .padding()
//                        .background(Color.purple)
//                        .cornerRadius(16)
//                        .foregroundColor(Color.white)
//                }
//                .buttonStyle(PlainButtonStyle())
//                : Button(action: {
//                    DispatchQueue.main.asyncAfter(deadline: .now() + 1.0) {
//                        withAnimation(){
//                        self.isActive = true
//                        }
//                    }
//                }){
//                    Text("Next")
//                        .padding()
//                        .background(Color.purple)
//                        .cornerRadius(16)
//                        .foregroundColor(Color.white)
//                }
//                .buttonStyle(PlainButtonStyle())
//
//
//            }
//
//        }
//        }
    
    }
}

struct OnBoardingView_Previews: PreviewProvider {
    static var previews: some View {
        OnBoardingView()
    }
}
