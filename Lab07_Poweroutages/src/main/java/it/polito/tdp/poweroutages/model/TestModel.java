package it.polito.tdp.poweroutages.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		//System.out.println(model.getNercList());
		Nerc n=new Nerc(1,"ERCOT");
		for(Blackout b:model.Soluzione(n, 2, 20)) {
			
		
		System.out.println(b);
		}
	}

}
