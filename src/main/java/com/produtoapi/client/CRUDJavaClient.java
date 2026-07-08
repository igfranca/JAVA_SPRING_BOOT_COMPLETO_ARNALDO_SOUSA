package com.produtoapi.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.produtoapi.model.Produto;
import java.util.Arrays;
import java.util.List;

public class CRUDJavaClient {

  private static final String BASE_URL = "http://localhost:8080/produtos";
  private RestTemplate restTemplate;

  public CRUDJavaClient() {
    this.restTemplate = new RestTemplate();
  }

  // Metodo para LISTAR todos os produtos
  public void listarTodos() {
    ResponseEntity<Produto[]> response = restTemplate.getForEntity(BASE_URL, Produto[].class);
    List<Produto> produtos = Arrays.asList(response.getBody());
    produtos.forEach(produto -> {
      System.out.println("ID: " + produto.getId());
      System.out.println("Nome: " + produto.getNome());
      System.out.println("Preço: " + produto.getPreco());
      System.out.println("Quantidade: " + produto.getQuantidade());
      System.out.println("Status: " + produto.getStatus());
      System.out.println("-------------------------------");
    });
  }

  // Metodo para SALVAR um novo produto
  public Produto salvar(Produto produto) {
    HttpEntity<Produto> request = new HttpEntity<>(produto);
    return restTemplate.postForObject(BASE_URL, request, Produto.class);
  }

  // Metodo para DELETAR um produto pelo ID
  public void deletar(long id) {
    restTemplate.delete(BASE_URL + "/" + id);
  }

  // Metedo para ATUALIZAR um produto existente
  public Produto atualizar(Long id, Produto produto) {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<Produto> request = new HttpEntity<>(produto, headers);
    ResponseEntity<Produto> response = restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.PUT, request, Produto.class);
    return response.getBody();
  }

  // Metodo para BUSCAR um produto pelo ID
  public Produto findByid(long id) {
    ResponseEntity<Produto> response = restTemplate.getForEntity(BASE_URL + "/" + id, Produto.class);
    return response.getBody();
  }

  // Metodo MAIN para fazer as chamadas
  public static void main(String[] args) {
    CRUDJavaClient client = new CRUDJavaClient();


    // CRIAR UM NOVO PRODUTO
    Produto novoProduto = new Produto();
    novoProduto.setNome("Caixa de Som DOIS");
    novoProduto.setPreco(1199.00);
    novoProduto.setQuantidade(11110);
    novoProduto.setStatus("Diponível");

    System.out.println("Criar um novo produto");
    client.salvar(novoProduto); // Salvar adição do novo produto
    client.listarTodos(); // Listar todos os produtos


//    // ATUALIZAR O PRODUTO
//    Produto atualizarProduto = client.findByid(152);
//    atualizarProduto.setNome("Caixa de Som Média");
//    atualizarProduto.setPreco(199.00);
//    atualizarProduto.setQuantidade(20);
//
//    System.out.println("Atualizar produto");
//    client.salvar(atualizarProduto.getId(), atualizarProduto); // Atualizar produto pelo ID e Produto
//    client.listarTodos(); // Listar todos os produtos
//
//
//    // DELETAR O PRODUTO
//    System.out.println("Deletar o produto");
//    client.deletar(202);
//    client.listarTodos(); // Listar todos os produtos
  }
}