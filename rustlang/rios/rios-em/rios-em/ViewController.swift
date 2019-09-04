//
//  ViewController.swift
//  rios-em
//
//  Created by yakir on 2019/6/12.
//  Copyright © 2019 yakir. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let result = rios_hello("from rust")
        let swift_result = String(cString: result!)
        rios_hello_free(UnsafeMutablePointer(mutating: result))
        print(swift_result)
    }
}
