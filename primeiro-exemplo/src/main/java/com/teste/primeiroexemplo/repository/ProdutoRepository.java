package com.teste.primeiroexemplo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.teste.primeiroexemplo.model.Produto;
import com.teste.primeiroexemplo.model.exceprion.ResourceNotFoundException;

@Repository
public class ProdutoRepository {
    
    private List<Produto> produtos = new ArrayList<>();
    private Integer ultimoId = 0;

    /**
     * Retorna uma lista de Produtos
     * @return Lista de Produtos
     */

    public List<Produto> obterTodos(){
        return produtos;
    }

 /**
  * Metodo que retorna o produto encontrado pelo seu ID.
  * @param id do produto que será localizado.
  * @return produto caso tenha sido encontrado.
  */
    public Optional<Produto> obterPorId(Integer id){
        return produtos
        .stream()
        .filter(produto -> produto.getId() == id)
        .findFirst();
    }

    /**
     * Metodo para adicionar produto na lista.
     * @param produto que será adicionado na lista.
     * @return produto que foi adicionado.
     */
    public Produto adicionar (Produto produto) {
        ultimoId ++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;
    }

    /**
     * Metodo para deletar produto pelo ID.
     * @param id do Produto que será deletado.
    */
    public void deletar(Integer id){
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Metodo para atualizar produto na lista.
     * @param produto que será atualizado na lista.
     * @return produto que foi atualizado.
     */
    public Produto atualizar (Produto produto) {
        Optional<Produto> produtoEncontrado  = obterPorId(produto.getId());
        if(produtoEncontrado.isEmpty()){
            throw new ResourceNotFoundException("Não Encontrado");
        }

        deletar(produto.getId());
        produtos.add(produto);
        
        return produto;
    }

}
