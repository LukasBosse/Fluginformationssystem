package com.fis.services;

import java.io.Writer;
import java.util.List;

import javax.annotation.ManagedBean;

import com.fis.model.Relationen;

@ManagedBean
public class RelationenDao extends AbstractDao {

	public RelationenDao(Writer oS) { super(oS); }
	
	public void createRelation(int startOrt, int zielOrt) {
		if(startOrt == zielOrt) {
			htmlWriter.writeAlert("Warnung!", "Ein Flughafen kann nicht gleichzeitig Start- und Zielort sein.", "alert-danger", "left");
			return;
		}
		Relationen r = new Relationen(startOrt, zielOrt);
		entityManager.getTransaction().begin();
		entityManager.persist(r);
		entityManager.getTransaction().commit();
		verify(r);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listRelationen() {
		return entityManager.createNativeQuery("SELECT relationen.ID, starthafen.Bezeichnung As `Starthafen`, landehafen.Bezeichnung As `Landehafen` FROM relationen INNER JOIN flughäfen As `starthafen` ON relationen.Startort = starthafen.ID INNER JOIN flughäfen As `landehafen` ON relationen.Zielort = landehafen.ID").getResultList();
	}
	
	public void verify(Relationen r) {
		if(r.getId() > 0) {
			htmlWriter.writeAlert("Erfolg!", "Die Relation wurden erfolgreich hinzugefügt.", "alert-success", "left");
		} else {
			htmlWriter.writeAlert("Warnung!", "Die Relation konnte nicht hinzugefügt werden. Bitte prüfen Sie Ihre Eingaben!", "alert-danger", "left");
		}
	}

}
