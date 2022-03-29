package service;

import dao.IDao;
import model.Endereco;

import java.util.List;

public class EnderecoService {
    private static IDao<Endereco> enderecoIDao;

    public EnderecoService(IDao<Endereco> enderecoIDao){
        this.enderecoIDao = enderecoIDao;
    }

    public Endereco salvar(Endereco endereco){
        enderecoIDao.salvar(endereco);
        return endereco;
    }

    public List<Endereco> buscarTodos(){
        return enderecoIDao.buscarTodos();
    }
}

