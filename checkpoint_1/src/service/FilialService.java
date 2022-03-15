package service;

import dao.IDao;
import model.Filial;

public class FilialService {
    private static IDao<Filial> filialIDao;

    public FilialService(IDao<Filial> filialIDao) {
        this.filialIDao = filialIDao;
    }

    public static Filial salvar(Filial filial){
        filialIDao.salvar(filial);
        return filial;
    }
}
