/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.church.view;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Victor Godoy
 */
public abstract class ViewHelper<T> {
    
    public abstract void setDados(HttpServletRequest request);
    public abstract T getDados();
}
