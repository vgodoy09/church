// JavaScript Document

$(document).ready(function() {
	$('#conteudoconfiguracao').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="500px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/clientesconfig.jsf"></iframe>');
	
	$('#menuClientes').click(function(){
		$('#conteudoconfiguracao').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="500px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/clientesconfig.jsf"></iframe>');
	});
	
	$('#menuCategorias').click(function(){
		$('#conteudoconfiguracao').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="500px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/categorias.jsf"></iframe>');
	});
	
	
});