import SwiftUI
import shared

@main
struct iOSApp: App {
    
    private let networkModuloe = NetworkModule()
    private let cacheModule = CacheModule()
    
	var body: some Scene {
		WindowGroup {
            PeopleListScreen(
                networkModule: <#T##NetworkModule#>,
                cacheModule: <#T##CacheModule#>
            )
        }
	}
}
