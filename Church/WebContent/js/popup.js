// JavaScript Document

var ClasseModal = {

        getBody: function(id){
                return (id.startsWith("fundo-conteudo-modal-") ? getBody() : window.parent.parent.document.getElementsByTagName("body")[0]);
        },

        createAndSet: function(id){
                var body = ClasseModal.getBody(id);
                if(!body){body = getBody();}

                var fundo = ClasseElemento.create("input");

                fundo.setAttribute("id",id);
                fundo.setAttribute("type","button");
                fundo.disabled = true;
                fundo.setAttribute("style","border: none; opacity:0.6; background- color: white; position: absolute; top: 0px; left: 0px; width: 100%;height: 100%; z-index: 100");

                ClasseElemento(fundo).appendTo(body);

                return fundo;

        },

        remove: function(id){
                var body = ClasseModal.getBody(id);
                for(var i = 0 ; body.childNodes.length ; i++){
                        var child = body.childNodes[i];
                        if(ClasseElemento.ELEMENT_NODE == child.nodeType && child.id == id){
                                child.parentNode.removeChild(child);
                                break;
                        }
                }
        },

        bloquearTela: function(want,idComponente){

                if(want){
                        var modal = null;
                        modal = ClasseModal.createAndSet("fundo-conteudo-modal-" + idComponente);
                        modal = ClasseModal.createAndSet("fundo-menu-modal-" + idComponente);
                        modal.style.width = "189px";
                        getById(idComponente).style.zIndex = 110;
                }else{
                        ClasseModal.remove("fundo-conteudo-modal-" + idComponente);
                        ClasseModal.remove("fundo-menu-modal-" + idComponente);
                }
        }
} 