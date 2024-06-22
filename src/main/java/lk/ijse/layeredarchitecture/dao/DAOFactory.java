package lk.ijse.layeredarchitecture.dao;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory==null) ? daoFactory = new DAOFactory():daoFactory;
    }

    public enum DAOType {
        CUSTOMER, ITEM, ORDER, ORDER_DETAIL, QUERY
    }

    public SuperDAO getDAO(DAOType daoType) {
        switch (daoType) {
            case CUSTOMER:
                return new lk.ijse.layeredarchitecture.dao.custom.impl.CustomerDAOImpl();

            case ITEM:
                return new lk.ijse.layeredarchitecture.dao.custom.impl.ItemDAOImpl();

            case ORDER_DETAIL:
                return new lk.ijse.layeredarchitecture.dao.custom.impl.OrderDetailsDAOImpl();

            case ORDER:
                return new lk.ijse.layeredarchitecture.dao.custom.impl.OrderDAOImpl();

            case QUERY:
                return new lk.ijse.layeredarchitecture.dao.custom.impl.QueryDAOImpl();

            default:
                return null;
        }
    }
}
