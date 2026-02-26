import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinInitializer.init()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}