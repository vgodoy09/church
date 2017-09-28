package br.com.church.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.church.modelo.Categoria;
import br.com.church.modelo.Transacao;
import br.com.church.modelo.Usuario;
import br.com.church.modelo.enuns.Status;
import br.com.church.util.Util;

public class DAOTransacao extends DAO<Transacao>{

	@Override
	public Transacao update(Transacao objeto, EntityManager em) {
		em.getTransaction().begin();
		objeto = em.merge(objeto);
		em.getTransaction().commit();
		return objeto;
	}

	@Override
	public void delete(Transacao objeto, EntityManager em) {
		objeto.setStatus(Status.INATIVO);
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
	}

	@Override
	public Transacao searchById(int id, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Transacao.class, id );
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Transacao> listAll(EntityManager em) {
		List<Transacao> Transacaos = new ArrayList<Transacao>();
		String sql = "select * from Transacao where status ='ATIVO'";
		em.getTransaction().begin();
		Query query = em.createNativeQuery(sql);
		Transacaos = (List<Transacao>) query.getResultList();
		return Transacaos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Transacao> listAll(Usuario usuario) throws ClassNotFoundException {
		List<Transacao> transacoes = new ArrayList<Transacao>();
		EntityManager em = null;
		try {
			em = Util.criaConexao();
			String sql = "select * from Transacao t inner join Contas c on t.idconta = c.id_conta where t.idcliente = ?1 and (c.nomeconta <> ?2 and c.nomeconta <> ?3) and t.status ='ATIVO'";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql,Transacao.class);
			query.setParameter(1, usuario.getId_usuario());
			query.setParameter(2, "Bens");
			query.setParameter(3, "Seguros");
			transacoes = (List<Transacao>) query.getResultList();
			return transacoes;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<Transacao> listAll(Usuario usuario, Date dateInitial, Date dateFinal, String tipoTransacao, Integer idCategoria, Integer idConta) throws ClassNotFoundException{
		List<Transacao> transacoes = null;
		EntityManager em = null;
		try {
			String string3 = "null";
			String string4 = "null";
			if(dateInitial != null && dateFinal != null){
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				string3 = sd.format(dateInitial);
				string4 = sd.format(dateFinal);
			}
			
			em = Util.criaConexao();
			String sql = "select * from FN_TransacoesByDate('"+string3+"', '"+string4+"', ?1, ?2, ?3, ?4) as "
					+ "(id_transacao integer, descricao character varying, idcategoria integer, "
					+ "idcliente integer, idconta integer, idlembrete integer, nota character varying, "
					+ "registrydate date, status character varying, tipotransacao character varying, "
					+ "valor double precision)";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql, Transacao.class);
			query.setParameter(1, usuario.getId_usuario());
			query.setParameter(2, tipoTransacao.equals("") ? "null" : tipoTransacao.isEmpty() ? "null" : tipoTransacao);
			query.setParameter(3, idCategoria == null ? 0 : idCategoria);
			query.setParameter(4, idConta == null ? 0 : idConta);
			transacoes = (List<Transacao>) query.getResultList();
			return transacoes;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<Transacao> listAll(Date dateInitial) throws ClassNotFoundException{
		List<Transacao> transacoes = null;
		EntityManager em = null;
		try {
			String string3 = "null";
			if(dateInitial != null){
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				string3 = sd.format(dateInitial);
			}
			
			em = Util.criaConexao();
			String sql = "select * from FN_AllTransacoesByDate('"+string3+"') as "
					+ "(id_transacao integer, descricao character varying, idcategoria integer, "
					+ "idcliente integer, idconta integer, idlembrete integer, nota character varying, "
					+ "registrydate date, status character varying, tipotransacao character varying, "
					+ "valor double precision)";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql, Transacao.class);
			transacoes = (List<Transacao>) query.getResultList();
			return transacoes;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<Transacao> listAllBensSeguros(Usuario usuario) throws ClassNotFoundException {
		List<Transacao> transacoes = new ArrayList<Transacao>();
		EntityManager em = null;
		try {
			em = Util.criaConexao();
			String sql = "select * from Transacao t inner join Contas c on t.idconta = c.id_conta where t.idcliente = ?1 and (c.nomeconta = ?2 or c.nomeconta = ?3) and t.status ='ATIVO'";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql,Transacao.class);
			query.setParameter(1, usuario.getId_usuario());
			query.setParameter(2, "Bens");
			query.setParameter(3, "Seguros");
			transacoes = (List<Transacao>) query.getResultList();
			return transacoes;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<Transacao> listUltimasTransacoes(Usuario usuario) throws ClassNotFoundException {
		List<Transacao> transacoes = new ArrayList<Transacao>();
		EntityManager em = null;
		try {
			em = Util.criaConexao();
			String sql = "select * from Transacao t  where t.idcliente = ?1 and t.status ='ATIVO'  order by t.registrydate desc  LIMIT 5";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql,Transacao.class);
			query.setParameter(1, usuario.getId_usuario());
			transacoes = (List<Transacao>) query.getResultList();
			return transacoes;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
	}
	
	public Double totalDespesas(Usuario usuario) throws ClassNotFoundException{
		Double despesas = null;
		EntityManager em = null;
		try {
			em = Util.criaConexao();
			String sql = "select SUM(t.valor) from Transacao t where t.idcliente = ?1 and t.status ='ATIVO' and t.tipotransacao='DESPESA'";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql);
			query.setParameter(1, usuario.getId_usuario());
			despesas = (Double) query.getSingleResult();
			return despesas;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
	}
	public Double totalReceitas(Usuario usuario) throws ClassNotFoundException{
		Double receitas = null;
		EntityManager em = null;
		try {
			em = Util.criaConexao();
			String sql = "select SUM(t.valor) from Transacao t where t.idcliente = ?1 and t.status ='ATIVO' and t.tipotransacao='RECEITA'";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql);
			query.setParameter(1, usuario.getId_usuario());
			receitas = (Double) query.getSingleResult();
			return receitas;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
	}
	

	@Override
	public void save(Transacao objeto, EntityManager em) {
		objeto.setStatus(Status.ATIVO);
		em.getTransaction().begin();
		em.persist(objeto);
		em.getTransaction().commit();
	}

	@Override
	public Transacao search(Transacao objeto, EntityManager em) {
		em.getTransaction().begin();
		return 	em.find(Transacao.class, objeto.getId_transacao());
	}

	@Override
	public Transacao searchByDescription(String description, EntityManager em) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Transacao> searchByDescription(String description, Date dateInitial, Date dateFinal, Usuario usuario) throws ClassNotFoundException {
		List<Transacao> transacoes = new ArrayList<Transacao>();
		EntityManager em = null;
		try {
			em = Util.criaConexao();
//			String sql = "select * from Transacao t where (( ?2 is null or ?3 is null) or t.registrydate between ?2 and ?3) and t.descricao like ?1 and idcliente = ?4 and status ='ATIVO'";
			String sql = "select * from Transacao t where t.descricao like ?1 and idcliente = ?2 and status ='ATIVO'";
			em.getTransaction().begin();
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Query query = em.createNativeQuery(sql,Transacao.class);
			query.setParameter(1, "%"+description+"%");
			
//			query.setParameter(2, dateInitial == null ? null : dateInitial);
//			Calendar datainicial = new GregorianCalendar();
//			datainicial.setTime(dateInitial);
//			query.setParameter(2, dateInitial, TemporalType.DATE);
//			query.setParameter(3, dateFinal == null ? null : dateFinal);
//			Calendar datafinal = new GregorianCalendar();
//			datafinal.setTime(dateFinal);
//			query.setParameter(3, dateFinal, TemporalType.DATE);
			query.setParameter(2, usuario.getId_usuario());
			transacoes = (List<Transacao>) query.getResultList();
			
			List<Transacao> transactions = null;
			if(dateInitial != null && dateFinal != null){
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				String string1 = sd.format(dateInitial);
				String string2 = sd.format(dateFinal);
				Date date1 = null;
				Date date2 = null;
				try {
					date1 = sd.parse(string1);
					date2 = sd.parse(string2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				transactions = new ArrayList<Transacao>();
				for(Transacao t : transacoes){
					if((t.getRegistryDate().before(date2) || t.getRegistryDate().equals(date2)) 
							&& (t.getRegistryDate().after(date1) || t.getRegistryDate().equals(date1))){
						transactions.add(t);
					}
//					System.err.println("registrydate " + t.getRegistryDate());
//					if(t.getRegistryDate().before(date2)){
//						System.out.println("registrydate é menor que date2");
//						System.out.println("date2 " + date2);
//					}else if (t.getRegistryDate().after(date2)){
//						System.out.println("registrydate é maior que date2");
//						System.out.println("date2 " + date2);
//					}
//					if(t.getRegistryDate().after(date1)){
//						System.out.println("registrydate é maior que date1");
//						System.out.println("date2 " + date1);
//					}else if(t.getRegistryDate().before(date1)){
//						System.out.println("registrydate é menor que date1");
//						System.out.println("date2 " + date1);
//					}
				}
				return transactions;
			}
			return transacoes;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            	erro.printStackTrace();
            }
        }
	}
	@SuppressWarnings("unchecked")
	public List<Transacao> searchByDescriptionBensSeguros(String description, Date dateInitial, Date dateFinal, Usuario usuario) throws ClassNotFoundException {
		List<Transacao> transacoes = new ArrayList<Transacao>();
		EntityManager em = null;
		try {
			em = Util.criaConexao();
			String sql = "select * from Transacao t inner join Contas c on t.idconta = c.id_conta where t.descricao like ?1 and t.idcliente = ?2 and (c.nomeconta = ?3 or c.nomeconta = ?4) and t.status ='ATIVO'";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql,Transacao.class);
			query.setParameter(1, "%"+description+"%");
			query.setParameter(2, usuario.getId_usuario());
			query.setParameter(3, "Bens");
			query.setParameter(4, "Seguros");
			transacoes = (List<Transacao>) query.getResultList();
			
			List<Transacao> transactions = null;
			if(dateInitial != null && dateFinal != null){
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				String string1 = sd.format(dateInitial);
				String string2 = sd.format(dateFinal);
				Date date1 = null;
				Date date2 = null;
				try {
					date1 = sd.parse(string1);
					date2 = sd.parse(string2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				transactions = new ArrayList<Transacao>();
				for(Transacao t : transacoes){
					if((t.getRegistryDate().before(date2) || t.getRegistryDate().equals(date2)) 
							&& (t.getRegistryDate().after(date1) || t.getRegistryDate().equals(date1))){
						transactions.add(t);
					}
				}
				return transactions;
			}
			return transacoes;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            	erro.printStackTrace();
            }
        }
	}
	@SuppressWarnings("unchecked")
	public List<Transacao> listAllForGrafic(Date dateInitial, Date dateFinal,Usuario usuario) throws ClassNotFoundException {
		List<Transacao> transacoes = new ArrayList<Transacao>();
		EntityManager em = null;
		try {
			em = Util.criaConexao();
			String sql = "select * from Transacao t inner join Contas c on t.idconta = c.id_conta where t.idcliente = ?1 and c.nomeconta <> ?2 and t.status ='ATIVO'";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql,Transacao.class);
			query.setParameter(1, usuario.getId_usuario());
			query.setParameter(2, "Bens");
			transacoes = (List<Transacao>) query.getResultList();
			List<Transacao> transactions = null;
			if(dateInitial != null && dateFinal != null){
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				String string1 = sd.format(dateInitial);
				String string2 = sd.format(dateFinal);
				Date date1 = null;
				Date date2 = null;
				try {
					date1 = sd.parse(string1);
					date2 = sd.parse(string2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				transactions = new ArrayList<Transacao>();
				for(Transacao t : transacoes){
					if((t.getRegistryDate().before(date2) || t.getRegistryDate().equals(date2)) 
							&& (t.getRegistryDate().after(date1) || t.getRegistryDate().equals(date1))){
						transactions.add(t);
					}
				}
				return transactions;
			}
			return transacoes;
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
	}
	
	public Double getValorTransacoesByCategoria(Date dateInitial, Date dateFinal,Usuario usuario, Categoria categoria) throws ClassNotFoundException {
//		TransacaoDTO transacao = new TransacaoDTO();
		Double valor = null; 
		EntityManager em = null;
		try {
			String string3 = "null";
			String string4 = "null";
			if(dateInitial != null && dateFinal != null){
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				string3 = sd.format(dateInitial);
				string4 = sd.format(dateFinal);
			}
			em = Util.criaConexao();
			String sql = "select * from fn_getvaluetransacao('"+string3+"', '"+string4+"', ?1, ?2)";
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql);
			query.setParameter(1, usuario.getId_usuario());
			query.setParameter(2, categoria.getId_categoria());
			valor = (Double) query.getSingleResult();
		}catch (NoResultException e){
		} finally {
            try {
            	em.close();
            } catch (Exception erro) {
            }
        }
		return valor;
	}

	@Override
	public Transacao atualizar(Transacao objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		return objeto;
		
	}

	@Override
	public void desativar(Transacao objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Transacao consultarPorId(int id, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transacao> listarTodos(Connection conexao) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transacao salvar(Transacao objeto, Connection conexao)
			throws SQLException {
		// TODO Auto-generated method stub
		return objeto;
	}
	
}
