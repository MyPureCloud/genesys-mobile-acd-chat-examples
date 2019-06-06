//
//  WebChatViewController.swift
//  WebChat Demo
//
//  Created by Chris Rumpf on 5/31/19.
//  Copyright 2019, Genesys
//
//  Permission is hereby granted, free of charge, to any person obtaining a 
//  copy of this software and associated documentation files (the "Software"),
//  to deal in the Software without restriction, including without limitation 
//  the rights to use, copy, modify, merge, publish, distribute, sublicense, 
//  and/or sell copies of the Software, and to permit persons to whom the 
//  Software is furnished to do so, subject to the following conditions:
//
//  The above copyright notice and this permission notice shall be included in 
//  all copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
//  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
//  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
//  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY 
//  CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, 
//  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
//  SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//

import UIKit
import WebKit

class WebChatViewController: UIViewController {

    var webView: WKWebView!

    override func viewDidLoad() {
        super.viewDidLoad()

        webView = WKWebView(frame: view.bounds)
        view.addSubview(webView)
      
        configureWebView()
        loadWebChat()
    }

    func configureWebView() {
        // to prevent scalability of content in the webview, we need to inject
        // some scale-related script
        let source: String = "var meta = document.createElement('meta');" +
            "meta.name = 'viewport';" +
            "meta.content = 'width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no';" +
            "var head = document.getElementsByTagName('head')[0];" + "head.appendChild(meta);";
        let script: WKUserScript = WKUserScript(source: source, injectionTime: .atDocumentEnd, forMainFrameOnly: true)
        webView.configuration.userContentController.addUserScript(script)

        // set up web chat callbacks
        webView.configuration.userContentController.add(self, name: "webchat")
    }

    func loadWebChat() {
        // Load Local Content
        guard let webChatUrl = Bundle.main.url(forResource: "webchatv2demo", withExtension: "html") else {
            return
        }

        // url.deletingLastPathComponent() part tells WebKit it can read from the directory that contains help.html – that’s a good place to put any assets such as images, JavaScript, or CSS.
        webView.loadFileURL(webChatUrl, allowingReadAccessTo: webChatUrl.deletingLastPathComponent())
    }
}

extension WebChatViewController: WKScriptMessageHandler {
    func userContentController(_ userContentController: WKUserContentController, didReceive message: WKScriptMessage) {
        if message.name == "webchat" {
            guard let webChatMessage = message.body as? Dictionary<String, Any>,
                  let webChatEvent = webChatMessage["event"] as? String else {
                return
            }

            print("webchat event: \(webChatEvent)")

            switch webChatEvent {
            case "WebChatService.ended", "WebChat.closed":
                if let navCtlr = self.navigationController {
                    navCtlr.popViewController(animated: true)
                } else {
                    self.dismiss(animated: true)
                }
            default:
                return
            }
        }
    }
}
