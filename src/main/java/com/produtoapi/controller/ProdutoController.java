package com.produtoapi.controller;

import com.produtoapi.model.Produto;
import com.produtoapi.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*") // "*" Permite requisições de qualquer origem
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
  @Autowired
  private ProdutoService produtoService;

  @GetMapping
  public List<Produto> listarTodos() {
    return produtoService.listarTodos();
  }

  @PostMapping
  public Produto salvar(@RequestBody Produto produto) {
    return produtoService.salvar(produto);
  }

  @PutMapping("/{id}")
  public Produto atualizar(@PathVariable Long id, @RequestBody
  Produto produto) {
    return produtoService.atualizar(id, produto);
  }

  @DeleteMapping("/{id}")
  public void deletar(@PathVariable Long id) {
    produtoService.deletar(id);
  }

  @GetMapping("/{id}")
  public Optional<Produto> findById(@PathVariable Long id) {
    return produtoService.findById(id);
  }

  @PostMapping("/salvarLista")
  public List<Produto> salvarLista(@RequestBody List<Produto> produtos) {
    return produtoService.salvarLista(produtos);
  }


  // Endpoints de busca por nome
  @GetMapping("/buscarPorNome")
  public List<Produto> buscarPorNome(@RequestParam String valor) {
    return produtoService.findByNome(valor);
  }
  @GetMapping("/buscarPorNomeContendo")
  public List<Produto> buscarPorNomeContendo(@RequestParam String valor) {
    return produtoService.findByNomeContaining(valor);
  }
  @GetMapping("/buscarPorNomeEStatus")
  public List<Produto> buscarPorNomeComecandoCom(@RequestParam String nome, @RequestParam String status) {
    return produtoService.findByNomeAndStatus(nome, status);
  }
  @GetMapping("/buscarPorNomeComecandoCom")
  public List<Produto> buscarPorNomeComecandoCom(@RequestParam String valor) {
    return produtoService.findByNomeStartingWith(valor);
  }
  @GetMapping("/buscarPorNomeTerminandoCom")
  public List<Produto> buscarPorNomeTerminandoCom(@RequestParam String valor) {
    return produtoService.findByNomeEndingWith(valor);
  }


  // Endpoints de busca por preço
  @GetMapping("/buscarPorPreco")
  public List<Produto> buscarPorPreco(@RequestParam Double valor) {
    return produtoService.findByPreco(valor);
  }
  @GetMapping("/buscarPorPrecoMaiorQue")
  public List<Produto> buscarPorPrecoMaiorQue(@RequestParam Double valor) {
    return produtoService.findByPrecoGreaterThan(valor);
  }
  @GetMapping("/buscarPorPrecoMenorQue")
  public List<Produto> buscarPorPrecoMenorQue(@RequestParam Double valor) {
    return produtoService.findByPrecoLessThan(valor);
  }
  @GetMapping("/buscarTotalPreco")
  public Double buscarTotalPreco() {
    return produtoService.findTotalPreco();
  }


  // Endpoints de busca por quantidade
  @GetMapping("/buscarPorQuantidade")
  public List<Produto> buscarPorQuantidade(@RequestParam Integer quantidade) {
    return produtoService.findByQuantidade(quantidade);
  }
  @GetMapping("/buscarPorQuantidadeMaiorQue")
  public List<Produto> buscarPorQuantidadeMaiorQue(@RequestParam Integer quantidade) {
    return produtoService.findByQuantidadeGreaterThan(quantidade);
  }
  @GetMapping("/buscarPorQuantidadeMenorQue")
  public List<Produto> buscarPorQuantidadeMenorQue(@RequestParam Integer quantidade) {
    return produtoService.findByQuantidadeLessThan(quantidade);
  }


  // Endpoints de busca por preço e status
  @GetMapping("/buscarPorStatus")
  public List<Produto> buscarPorStatus(@RequestParam(required = false) String valor) {
    return produtoService.findByStatus(valor);
  }
  @GetMapping("/buscarPorStatusNulos")
  public List<Produto> buscarPorStatusNulos() {
    return produtoService.findByStatusIsNull();
  }
  @GetMapping("/buscarPorPrecoEStatus")
  public List<Produto> buscarPorPrecoEStatus(@RequestParam Double preco, @RequestParam String status) {
    return produtoService.findByPrecoAndStatus(preco, status);
  }
  @GetMapping("/contarTotalDeProdutos")
  public Long contarTotalDeProdutos() {
    return produtoService.count();
  }
  @GetMapping("/buscarPorStatusPadrao")
  public List<Produto> buscarPorStatusPadrao(@RequestParam(defaultValue = "Disponível") String valor) {
    return produtoService.findByStatus(valor);
  }
}