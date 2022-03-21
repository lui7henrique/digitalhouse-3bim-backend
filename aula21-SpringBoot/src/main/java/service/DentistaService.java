package service;

import dao.IDao;
import model.Dentista;
import java.util.List;

public class DentistaService {
    private IDao<Dentista> dentistaIDao;

    public DentistaService(IDao<Dentista> dentistaIDao) {
        this.dentistaIDao = dentistaIDao;
    }

    public Dentista salvar(Dentista dentista){
        dentistaIDao.salvar(dentista);
        return dentista;
    }

    public List<Dentista> buscarTodos(){
        return dentistaIDao.buscarTodos();
    }
}