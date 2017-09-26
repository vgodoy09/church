/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.church.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.church.modelo.Menu;

/**
 *
 * @author Victor Godoy
 */
public class ViewHelperMenu extends ViewHelper<Menu>{
    private Menu menu;
    @Override
    public void setDados(HttpServletRequest request) {
        String id = request.getParameter("txtid");
        Integer menuId = null;
        if(id != null && !id.isEmpty()){
	        try {
	        	menuId = Integer.parseInt(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        
        HttpSession session = request.getSession(true);
		String idapp = (String) session.getAttribute("idAp");
		
        String nome = request.getParameter("name");
        String image = request.getParameter("image");
        String link = request.getParameter("link");
        String menus = request.getParameter("menus");
        Integer fathermenu_id = null;
        if(menus != null && !menus.isEmpty()){
        	fathermenu_id = Integer.parseInt(menus);
        }
        Integer application_id = Integer.parseInt(idapp);
        menu = new Menu();
        if(menuId != null)
        	menu.setId(menuId);
        menu.setName(nome);
        menu.setImage(image);
        menu.setLink(link);
        menu.setFathermenu_id(fathermenu_id);
        menu.setApplication_id(application_id);
    }

    @Override
    public Menu getDados() {
        return menu;
    }
    
}
