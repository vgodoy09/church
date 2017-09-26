// JavaScript Document

$(document).ready(function() {
	$('#conteudogfre').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="1021px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/retransacao.jsf"></iframe>');
	
	$('#menuReTrasacao').click(function(){
		$('#conteudogfre').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="1021px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/retransacao.jsf"></iframe>');
	});
	
	$('#menuRePagarReceber').click(function(){
		$('#conteudogfre').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="1021px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/pagarreceber.jsf"></iframe>');
	});
	
	$('#menuReBensSeguros').click(function(){
		$('#conteudogfre').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="1021px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/bensseguros.jsf"></iframe>');
	});
	
	$('#menuGfTransacao').click(function(){
		$('#conteudogfre').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="1021px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/graficostransacao.jsf"></iframe>');
	});
	
	$('#menuGfPagarReceber').click(function(){
		$('#conteudogfre').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="1021px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/gfpagarreceber.jsf"></iframe>');
	});
	
	$('#menuGfBensSeguros').click(function(){
		$('#conteudogfre').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="1021px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/gfbensseguros.jsf"></iframe>');
	});
	
});