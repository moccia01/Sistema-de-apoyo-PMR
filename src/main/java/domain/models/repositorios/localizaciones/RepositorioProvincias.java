package domain.models.repositorios.localizaciones;

public class RepositorioProvincias {

   /* public void agregarGrado(GradoDeConfianza gradoDeConfianza){
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(gradoDeConfianza);
        tx.commit();
    }

    public class RepositorioProvincias implements WithSimplePersistenceUnit {
        public List<Provincia> obtenerProvincia(){
            return entityManager()
                    .persist()
                    .getResultList();
        }

        public void provinciaEnNuestraBase(List<Provincia> provincias){
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction tx = entityManager.getTransaction();

            try {
                tx.begin();
                for (Provincia provincia : provincias) {
                    entityManager.persist(provincia);
                }
                tx.commit();
            } catch (Exception e) {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
                e.printStackTrace();
            } finally {
                entityManager.close();
            }
     
        }

    }*/



}
