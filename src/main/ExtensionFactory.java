/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import extension.IExtension;
import extension.IExtensionFactory;
import extension.MyExtensionFrame;
import host.IHostApplication;
import sortvisualiser.MainApp;

/**
 *
 * @author duongtb
 */
public class ExtensionFactory implements IExtensionFactory {
    public IExtension createExtension(IHostApplication inHostApp)
    {
        MainApp mainApp = new MainApp();
        mainApp.setHostApp(inHostApp);
        return mainApp;
    }
}
