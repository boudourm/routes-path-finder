package Package;

import java.util.ArrayList;
import java.util.Scanner;




//Projet realise par:   
//BOUDOUR Mehdi     Matricule : 201500008386 
 
public class Application {
    
	//Tableau des villes creer (Base de donnees)
	public static ArrayList<Ville> ListeVilles =new ArrayList <Ville> ();
	
	//Objet scanner
	static Scanner saisir =new Scanner (System.in);
	
	//*********************************************************************MAIN************************************************************************
	public static void main(String[] args)
	{	
		System.out.println("Ce Projet a ete realise par: \n  -BOUDOUR Mehdi \n ISIL A");
		Menu ();	
	}
	
	//****************************************************************Methodes statiques****************************************************************
	
	//11- Recherche d'une ville par son nom
	public static Ville RechercheVille (String nom)
	  {
		  for (Ville ville: ListeVilles)
		  {
			  if(ville.getNom().equalsIgnoreCase(nom)) return ville;
			  
		  }
		  return null;
	  }
	
	
	// 11- Supression d'une ville
	public static void Suppressionville (String nom )
	{
		ListeVilles.remove(RechercheVille(nom));
		for (int i=0;i<ListeVilles.size();i++)
		{
			ListeVilles.get(i).getListePred().remove(RechercheVille(nom));
			ListeVilles.get(i).getListeSucc().remove(RechercheVille(nom)); 
			
		}
	}
	
	// L'ajout d'une ville creer au tableau des villes
	public static void AjoutVille (Ville ville )
	{
		ListeVilles.add(ville);
	}
	
	//12- Affichage du Reseau routier des villes
	public static void ReseauxRoutier()
	{
		System.out.println("Affichage du Résaux Routier  : \n");
		for(Ville V : ListeVilles)
		{
			System.out.println("\nVille "+V.getNumerotation()+
					            " : "+V.getNom());
			
			System.out.println("Elle Mene Vers : ");
			if(V.getListeSucc().isEmpty()) System.out.println("Nullpart !");
			for(Ville T : V.getListeSucc())
			{
		     System.out.println("    --->"+T.getNom());
			}
		}
	}

	
	//11-Modifier les informations d'une ville
	public static void ModifierVille()
	{
		//-----------------CHOIX DE LA VILLE A MODIFIER-------------------------------------
		System.out.println("Modification D'une Ville :");
		
		System.out.println("Choisissez une Ville a Modifier :");
		AfficherVilles();
		System.out.println("Entrez le Nom de la Ville Choisie : ");
		String nom = saisir.nextLine();
		Ville ville = RechercheVille(nom);
		while(ville==null)
		{
			System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
			nom = saisir.nextLine();
			ville = RechercheVille(nom);
		}
		//-----------------BOUCLE APPLANTE DU SOUS MENU DE LA MODIF --------------------------
		boolean Quitter =false;
		while(!Quitter)
		{
			//------------------------AFFICHAGE DES OPTIONS ------------------------------
			System.out.println("Choisissez une Option :\n");
			System.out.println("[1]Modifier le Nom .\n"
					         + "[2]Modifier la Wilaya .\n"
					         + "[3]Modifier la Superficie .\n"
					         + "[4]Modifier le Nombre d'Habitants .\n"
					         + "[5]Modifier le Type .\n"
					         + "[6]Modifier le Classment .\n"
					         + "[7]Modifier les Voisines .\n"
					         + "[8]Modifier une Autre Ville .\n"
					         + "[9]Quitter La Modification.");
			System.out.println("Entrez un Numero : ");
			int select = saisir.nextInt();
			while(select<1 || select>9)
			{
				System.out.println("Saisie Invalide ! Veuillez Reessayez svp :");
				select = saisir.nextInt();
			}
			saisir.nextLine();
			//-------------SWITCH DES OPTIONS DE MODIFICATION----------------------------
			switch(select)
			{//----------------MODIF NOM--------------------------------------------------
			case 1 : System.out.println("Saisissez le nouveau Nom : ");
			         nom = saisir.nextLine();
			         while(RechercheVille(nom) !=null)
	          		 {
				      System.out.println("Ce nom existe déja !"
				      		           + " Veuillez Reessayez svp :");
				      nom = saisir.nextLine();
		        	 }
			         ville.setNom(nom); 
			         break;
		//-------------------------MODIF WILAYA--------------------------------------------	         
			case 2 : System.out.println("Entrez la Wilaya :");
                	 String wilaya = saisir.nextLine();
                	 ville.setWilaya(wilaya);
    		         break;
    		     	
        //--------------------MODIF Superficie---------------------------------------------        	 
			case 3 : 	System.out.println("Entrez la Superficie (Km²):");
		             	float Superficie = saisir.nextFloat();
		             	while(Superficie<=0  )	
	            		{
            				System.out.println("Superficie Invalide !"
            						         + " Veuillez Reessayez svp : ");
			             	Superficie = saisir.nextFloat();
               			}
		             	ville.setSuperficie(Superficie);
		             	saisir.nextLine();
				         break;
				     	
		//-----------------------MODIF NOMBRE D'HABITANTS----------------------------------
			case 4 :    System.out.println("Entrez le nombre d'Habitants : ");
			            int habitants = saisir.nextInt();
			            while(habitants<=0 )	
	             		{
	                			System.out.println("Nombre d'Habitants  Invalide !"
	                				            	+ " Veuillez Reessayez svp : ");
                				habitants = saisir.nextInt(); 
	              		}
			            ville.setNbreHabit(habitants);
			            saisir.nextLine(); 
			            break;
				     	
		//-------------------------MODIF TYPE----------------------------------------------	            
			case 5 : System.out.println("Choisissez le type de la Ville : ");
			         //Mini Menu pour les Types
			         int select1 ;
			         System.out.println("1-Agricole (entrez 1)."
					         + "\n2-Touristique (entrez 2.)"
					         + "\n3-Industrielle (entrez 3.)"
					         + "\n4-Ordinaire (entrez 4).");
	        
			         System.out.println("Entrez un Numero : "); select1 = saisir.nextInt();
			         while(select1<1 || select1>4)
		         	 {
				     System.out.println("Saisie Invalide ! Veuillez Reessayez svp : ");
				     select1 = saisir.nextInt();
		        	 }
			         int type = select1 -1;
			         ville.setType(type);
			         break;
			     	
//--------------------------------MODIF CLASSEMENT-----------------------------------------			         
			case 6 :  System.out.println("Choisissez le Classement (Fleurs) de la Ville : ");
			          //Mini Menu pour les Classments
			          System.out.println("1-Tres Fleurie (entrez 1).\n"
					         + "2-Bien Fleurie (entrez 2).\n"
					         + "3-Assez Fleurie (entrez 3).\n"
					         + "4-Peu Fleurie (entrez 4).\n"
					         + "5-Pas du tout Fleurie (entrez 5).\n"); 
			          System.out.println("Entrez un Numero : "); select = saisir.nextInt();
			          while(select<1 || select>5)
			          {
				          System.out.println("Saisie Invalide ! "
				          		           + "Veuillez Reessayez svp : ");
				          select = saisir.nextInt();
			          }
			           int classement = select -1;
			           ville.setClassement(classement);
			           saisir.nextLine(); 
			           break;
				     	
	//---------------------------------MODIF VOISINES-------------------------------		           
	//----------------------------SELECTIONNER SUCC ou PRED-----------------------
			case 7 :   System.out.println("Modification des Voisines :"
				                      	+ "Choisissez une Option :");
			           System.out.println("1-Successeurs .(entrez 1)\n"
			           		            + "2-Predecesseurs .(entrez 2)\n"
			           		            + "3-Retour au Menu ''Modification''. (entrez 3)");
			           System.out.println("Entrez un Numero :");
			           int select2 = saisir.nextInt();
			           while(select2<1 || select2>3)
				          {
					          System.out.println("Saisie Invalide ! "
					          		           + "Veuillez Reessayez svp : ");
					          select2 = saisir.nextInt();
				          }
			           //-------------SWITCH Pred ou Succ -----------------------------------
			           switch(select2)
			           {//Case 1 = Succ
			           case 1 :   System.out.println("Quelle Traitement Voulez-Vous effectuer ?"
		                                           + "Choisissez une Option :");
	                              System.out.println("1-Ajouter un Successeur .(entrez 1)\n"
	                            		           + "2-Supprimer un Successeur .(entrez 2)\n"
	                            		           + "3-Retour au Menu ''Modification''.(entrez 3)");
	                              System.out.println("Entrez un Numero :");
	                              int select3 = saisir.nextInt();
	                              while(select3<1 || select3>3)
		                          {
			                           System.out.println("Saisie Invalide ! "
			          		                            + "Veuillez Reessayez svp : ");
			                           select3 = saisir.nextInt();
		                          }
	                              saisir.nextLine();
	                              //Ajout ou Supp (Succ Mode)------------------------------------
	                              switch(select3)
	                              {
	                              case 1 :System.out.println("Ajout d'un Successeur :");
	                                      System.out.println("Choisissez une Ville : ");        
	                                      AfficherVilles();
	                      		          System.out.println("Entrez le Nom de la Ville Choisie : ");
	                      	              nom = saisir.nextLine();
	                      		          Ville succ = RechercheVille(nom);
	                      		          while(succ==null || succ == ville)
	                      	          	  {
	                      		        	if(succ == ville)
		                                	  {
		                                		  System.out.println("Liaison Invalide ! Reessayez svp .");
		                                	  }
		                                	  else
		                                	  {
		                                		System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
      			                              
		                                	  }
	                      			      nom = saisir.nextLine();
	                      			      succ = RechercheVille(nom);
	                      		          } 
	                      		          //Ajout d'un Succ
	                      		          ville.addSucc(succ);;
	                      		          //Chainage
	                      		          succ.addPred(ville);
	                     		         break;
	                     		    	
	                              case 2 : if(ville.getListeSucc().isEmpty())
	                                        {
	                            	           System.out.println("->"+ville
	                            	        		              .getNom()+
	                            	        		              " :N'a pas "
	                            	        		              + "de Successeurs !");
	                                        }
	                                       else
	                                       {
	                                    	   System.out.println("Suppression d'un Successeur :");
	                                           System.out.println("Choisissez une Ville : ");        
	                                           AfficheListeSucc(ville);
	                   		                  System.out.println("\nEntrez le Nom de la Ville Choisie : ");
	                   	                      nom = saisir.nextLine();
	                   		                  succ = ville.RechercheSucc(nom);
	                   		                  while(succ==null || succ ==ville)
	                   	          	          {
	                   		                	if( succ == ville)
    		                                	  {
    		                                		  System.out.println("Liaison Invalide ! Reessayez svp .");
    		                                	  }
    		                                	  else
    		                                	  {
    		                                		System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
          			                              
    		                                	  } 
	                   			              nom = saisir.nextLine();
	                   			              succ = ville.RechercheSucc(nom);
	                   		                  } 
	                   		          //Supp d'un Succ
	                   		                  ville.removeSucc(succ);
	                   		                  //détruire Chainage
	                   		                  succ.removePred(ville);
	                   				         break;
	                   				 
	                                       }
	                              case 3 :  break;
	                              }
	                              break;
	                    //case 2 = Pred          
			           case 2 :   System.out.println("Quelle Traitement Voulez-Vous effectuer ?"
                                  + "Choisissez une Option :");
                                   System.out.println("1-Ajouter un Predecesseur .(entrez 1)\n"
            		              + "2-Supprimer un Predecesseur .(entrez 2)\n"
                                  + "3-Retour au Menu ''Modification''. (entrez 3)");
                                   System.out.println("Entrez un Numero :");
                                   select3 = saisir.nextInt();
                                   while(select3<1 || select3>3)
                                    {
                                  System.out.println("Saisie Invalide ! "
  		                            + "Veuillez Reessayez svp : ");
                                    select3 = saisir.nextInt();
                                   }
                                   saisir.nextLine();
                                  //Ajout ou Supp (Pred Mode)------------------------------------
                                    switch(select3)
                                    {
                                   case 1 :System.out.println("Ajout d'un Predecesseur :");
                                             System.out.println("Choisissez une Ville : ");        
                                               AfficherVilles();
      		                                 System.out.println("Entrez le Nom de la Ville Choisie : ");
      	                                     nom = saisir.nextLine();
      		                                Ville pred = RechercheVille(nom);
      		                                  while(pred==null || pred==ville)
      	                              	     {
      		                                	  if(pred == ville)
      		                                	  {
      		                                		  System.out.println("Liaison Invalide ! Reessayez svp .");
      		                                	  }
      		                                	  else
      		                                	  {
      		                                		System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
            			                              
      		                                	  }
      			                               nom = saisir.nextLine();
      			                               pred = RechercheVille(nom);
      		                                  } 
      		                               //L'ajout d'un Pred
      		                               ville.addPred(pred);
      		                               //Chainage
      		                               pred.addSucc(ville);
      		            		         break;
      		            		   	
      		          
                                   case 2 : if(ville.getListePred().isEmpty())
                                              {
            	                              System.out.println("->"+ville
            	        		                .getNom()+
            	        		                " :N'a pas "
            	        		               + "de Predecesseurs !");
                                               }
                                             else
                                             {
                                         	   System.out.println("Suppression d'un Predecessuer :");
                                                System.out.println("Choisissez une Ville : ");        
                                             AfficheListePred(ville);
   		                                       System.out.println("\nEntrez le Nom de la Ville Choisie : ");
   	                                             nom = saisir.nextLine();
   		                                          pred = ville.RecherchePred(nom);
   		                                       while(pred==null || pred == ville)
   	          	                              {
   		                                    	if( pred == ville)
    		                                	  {
    		                                		  System.out.println("Liaison Invalide ! Reessayez svp .");
    		                                	  }
    		                                	  else
    		                                	  {
    		                                		System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
          			                              
    		                                	  }
   			                                  nom = saisir.nextLine();
   			                                   pred = ville.RechercheSucc(nom);
   		                                        } 
   		                                      //Supp d'un Pred
   		                                     ville.removePred(pred);
   		                                     //détruire Chainage
   		                                     pred.removeSucc(ville);
   		                 		         break;
   		                 		 	
                                             }
                                   case 3 :  break;
                                        }
			                     case 3 :  break;
 			           }
			           break;
		//---------------------------Changer de Ville-------------------------------	           
			case 8 :  System.out.println("Choisissez une Ville a Modifier :");
			          AfficherVilles();
			          System.out.println("Entrez le Nom de la Ville Choisie : ");
			          nom = saisir.nextLine();
			          ville = RechercheVille(nom);
		              while(ville==null)
		         	  {
				              System.out.println("Ville Introuvable ! "
				              		           + "Veuillez Reessayez svp :");
				              nom = saisir.nextLine();
				              ville = RechercheVille(nom);
			          }
		              break ;
			case 9 : Quitter =true; break;
			}
		}
	}
	
	
	//13-Affichage des villes par couleur
	public static void VilleCouleur()
	{
		System.out.println("Listes des Villes Par Couleurs :");
		// Vertes
		System.out.println("Liste des Villes Vertes :");
		boolean b = false ;
		for(int i = 0 ; i<ListeVilles.size() ; i++)
		{
			
			if(ListeVilles.get(i).getCouleur().equalsIgnoreCase("Verte"))
			{
				System.out.println("- La Ville "+ListeVilles.get(i).getNom());
				b = true;
			}
		}
		if(!b) System.out.println("Il n'y a aucune ville Verte !!!");
		
		//Orange
		System.out.println("Liste des Villes Orange :");
		b = false ;
		for(int i = 0 ; i<ListeVilles.size() ; i++)
		{
			if(ListeVilles.get(i).getCouleur().equalsIgnoreCase("Orange"))
			{
				System.out.println("- La Ville "+ListeVilles.get(i).getNom());
				b = true;
			}
		}
		if(!b) System.out.println("Il n'y a aucune ville Orange !!!");
		
		System.out.println("Liste des Villes Rouges :");
		 b = false ;
		for(int i = 0 ; i<ListeVilles.size() ; i++)
		{
			if(ListeVilles.get(i).getCouleur().equalsIgnoreCase("Rouge"))
			{
				System.out.println("- La Ville "+ListeVilles.get(i).getNom());
				b = true;
			}
		}
		if(!b) System.out.println("Il n'y a aucune ville Rouge !!!");
	}
	
	//15-Partitionnement des villes en composantes fortement connexes
	public static void Partitionnement()
	{
		for(int i = 0 ; i<ListeVilles.size() ; i++)
		{
			if(ListeVilles.get(i).getPartie()==0)
			{
				ListeVilles.get(i).Successeur();
				ListeVilles.get(i).Predecesseur();
				
				
				//Effacer
				Ville.IncPartie();
				for(int j = 0 ; j<ListeVilles.size() ; j++)
				{
					Ville V =ListeVilles.get(j) ; 
					if(V.isPlus()==true && V.isMoins()==true)
						{
				     		V.setPartie();
				     		
						}
					V.setMoins(false);
		     		V.setPlus(false);
				}
			}
		}
	}
		
	//15-Affichage des composantes fortement connexes
		public static void AfficherComposantes()
		{
			//Former les Composantes fortements Connexes
			Partitionnement();
			//Collecte des composantes fortements connexes
			int partie =1;
			ArrayList<Ville> Array = Ramasser(partie);
			ArrayList<ArrayList<Ville>> TAB =new ArrayList<ArrayList<Ville>>(); 
			while(!Array.isEmpty())
			{
				partie++;
				TAB.add(Array);
				Array = Ramasser(partie);
			}
			
			System.out.println("Les Composantes Fortement Connexes : ");
			int i = 1 ; 
			for(ArrayList<Ville> partition : TAB)
			{
				System.out.println("\nLa Partion N°"+i+" : ");
				for(Ville V : partition)
				{
					System.out.println("-"+V.getNom());
				}
				i++;
			}
		}
		
		
		//15-Utiliser pour l'affichage des composantes fortement connexes 
		public static ArrayList<Ville> Ramasser(int partie)
		{
			ArrayList <Ville> Composante =new ArrayList<Ville> ();
			
			for(int i=0; i<ListeVilles.size();i++)
			{
				if (ListeVilles.get(i).getPartie()==partie) 
					Composante.add(ListeVilles.get(i));
			}
			return Composante;
		}
		
		
	// Methode creation d'une ville 
	
	public static void CreationVille ()
	{
		
		System.out.println("Etrez les Information de la nouvelle ville svp :");
		System.out.println("Entrez le Nom : ");
		String nom = saisir.nextLine();
		
		Ville ville = RechercheVille(nom); 
		while(ville != null)
		{
			System.out.println("Ce nom existe deja ! Veuillez reessayez svp :");
			nom = saisir.nextLine();
			ville = RechercheVille(nom);
		}
		System.out.println("Entrez la Wilaya :");
		String wilaya = saisir.nextLine();
		
		System.out.println("Entrez la Superficie (Km²):");
		float Superficie = saisir.nextFloat();
		while(Superficie<=0  )	
		{
			System.out.println("Superficie Invalide ! Veuillez reessayez svp : ");
			Superficie = saisir.nextFloat();
		}
		
		System.out.println("Entrez le nombre d'Habitants : ");
		int habitants = saisir.nextInt();
		while(habitants<=0 )	
		{
			System.out.println("Nombre d'Habitants  Invalide ! Veuillez reessayez svp : ");
			habitants = saisir.nextInt();
		}
		
		System.out.println("Choisissez le type de la Ville : ");
		//Mini Menu pour les Types
		int select ;
		System.out.println("1-Agricole (entrez 1)."
				         + "\n2-Touristique (entrez 2.)"
				         + "\n3-Industrielle (entrez 3.)"
				         + "\n4-Ordinaire (entrez 4).");
        
		System.out.println("Entrez un Numero : "); select = saisir.nextInt();
		while(select<1 || select>4)
		{
			System.out.println("Saisie Invalide ! Veuillez Reessayez svp : ");
			select = saisir.nextInt();
		}
		int type = select -1;
	   
		System.out.println("Choisissez le Classement (Fleurs) de la Ville : ");
		//Mini Menu pour les Classment
		System.out.println("1-Tres Fleurie (entrez 1).\n"
				         + "2-Bien Fleurie (entrez 2).\n"
				         + "3-Assez Fleurie (entrez 3).\n"
				         + "4-Peu Fleurie (entrez 4).\n"
				         + "5-Pas du tout Fleurie (entrez 5).\n");
        
		System.out.println("Entrez un Numero : "); select = saisir.nextInt();
		while(select<1 || select>5)
		{
			System.out.println("Saisie Invalide ! Veuillez Reessayez svp : ");
			select = saisir.nextInt();
		}
		saisir.nextLine();
		int classement = select -1;
		
		AjoutVille(new Ville(nom , wilaya ,   habitants , Superficie,type , classement));
		
		
	}
	
	//Afficher la liste des villes creer
	public static void AfficherVilles()
	{
                
		for (int i=0;i<ListeVilles.size();i++)
		{
			System.out.println(ListeVilles.get(i).getNom());
		}
	}
	
	
	
	//------------------------Lier 2 Villes-------------------------------------------
			public static void Lier()
			{		System.out.println("Liaison de 2 Ville :");
				//-------------------------Saisie des 2 villes a liee
				System.out.println("Choisissez Les 2  Villes que vous voulez Lier :");
				AfficherVilles();
			    //---------------------Ville de DEPART----------------------------------------
				System.out.println("Entrez la Ville de depart : ");
				String nom = saisir.nextLine();Ville depart = RechercheVille(nom);
				while(depart==null)
				{			System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
					nom = saisir.nextLine();
					depart = RechercheVille(nom);	}
				//--------------------------VILLE d'arrivee------------------------------------
				System.out.println("Entrez la Ville d'arrivee : ");
				nom = saisir.nextLine();Ville arrivee = RechercheVille(nom);
				while(arrivee==null ||arrivee == depart )
				{
					if(arrivee == depart)
		      	  {  System.out.println("Liaison Invalide ! Reessayez svp .");    	  }
		      	  else
		      	  {	System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");   }
					nom = saisir.nextLine();
					arrivee = RechercheVille(nom);
				}
				//----------------------------------CHAINAGE---------------------------------
				depart.addSucc(arrivee);
				arrivee.addPred(depart);
				//---------------------------Résultat------------------------------------------------
				System.out.println("La Liaison : [ "+depart.getNom()+" --->  "+arrivee.getNom()+" ]"+ " a ete effectue avec Succes !");
			}
	
		
		
		//Affiche les Predecesseurs d'une ville
	public static void  AfficheListePred(Ville V)
	{
		for(Ville T : V.getListePred())
		{
			System.out.println("-"+T.getNom());
		}
	}

	//Affiche les Successeurs d'une ville
	public static void  AfficheListeSucc(Ville V)
	{
		for(Ville T : V.getListeSucc())
		{
			System.out.println("-"+T.getNom());
		}
	}

	
	
	
	
	
	
	
	// Menu principal englobant toute les methodes
	public static void Menu ()
	{
		Ville ville;
		boolean quitter=false;
		int select;
		String nom;
		
		while (!quitter)
		{
		    System.out.println("*******************************************************************************Bienvenue*******************************************************************************");
		    System.out.println("On vous propose plusieurs fonctionalites, vous n'avez qu'a choisir la quelle que vous voulez executer:");
		    System.out.println("[1] Creer une nouvelle ville.");
		    System.out.println("[2] Modifier une ville.");
		    System.out.println("[3] Rechercher une ville.");
		    System.out.println("[4] Supprimer une ville.");
		    System.out.println("[5] Lier deux villes.");
		    System.out.println("[6] Savoir si une ville est isolee.");
		    System.out.println("[7] Savoir si une ville mene a exactement 3 autres villes.");
		    System.out.println("[8] Savoir si le type d'une ville est different de celui ce ses voisines.");
		    System.out.println("[9] Savoir si une ville est plus fleurie que ses voisines.");
		    System.out.println("[10] Savoir si une ville est verte, a un type non ordinaire et  n'est entouree que par des villes rouges.");
		    System.out.println("[11] Savoir si le chemin partant de la ville ville1 passant directement  par les villes ville2, ..., ville n-1,et se terminant par la ville vn est realisable.");
		    System.out.println("[12] Savoir s'il existe une chemin qui mene d'une ville1 vers une autre ville2 donnee.");
		    System.out.println("[13] Afficher les informations d'une ville.");
		    System.out.println("[14] Afficher les villes creer.");
		    System.out.println("[15] Afficher le reseaux routier des villes crees.");
		    System.out.println("[16] Afficher les villes par couleur.");
		    System.out.println("[17] Afficher les partitions des villes.");
		    System.out.println("[18] Quitter.");
		    
		    System.out.println("Entrez le numero de la methode que vous voulez executer :");
		    select=saisir.nextInt();
		    saisir.nextLine();
		    while(select<1 || select>18)
			{
				System.out.println("Saisie Invalide ! Veuillez Reessayez svp :");
				select = saisir.nextInt();
			}
		    switch(select)
		    {
		    
		    case 1: System.out.println("-----------------------------------------Creation d'une Ville ------------------------------------------------------------");
		    	    
		    	    CreationVille ();
		            break;
		            
		    case 2: System.out.println("-----------------------------------------Modification d'une Ville -------------------------------------------------------");
		            if(ListeVilles.isEmpty())
			        {
				           System.out.println("Aucune Ville n'a ete Creee ! ");
			        }
			         else
			        {
			               ModifierVille ();
			        }
                    break;
                    
		    case 3: System.out.println("-----------------------------------------Recherche d'une Ville ----------------------------------------------------------");
		              if(ListeVilles.isEmpty())
	                  {
		                     System.out.println("Aucune Ville n'a ete Creee ! ");
	                  }
	                 else
	                 {
	                	 System.out.println("Introduisez le nom de la ville que vous voulez rechercher");
	 		            nom=saisir.nextLine();
	 		            ville=RechercheVille(nom);
	 		            if(ville==null) System.out.println("la ville "+nom+" n'existe pas.");
	 		            else ville.AfficherVille();    
	                 }
                          
		            break;
		            
		    case 4: System.out.println("-----------------------------------------Suppression d'une Ville --------------------------------------------------------");
		    	   
		    if(ListeVilles.isEmpty())
	        {
		           System.out.println("Aucune Ville n'a ete Creee ! ");
	        }
	         else
	        {
	        	 System.out.println("Choisissez une Ville  :");
                 
                 AfficherVilles();
		    	    System.out.println("Introduisez le nom de la ville que vous voulez supprimer");
                 nom=saisir.nextLine();
                 ville=RechercheVille(nom);
		            while (ville==null)
		            {
		            	System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
		            	nom=saisir.nextLine();
			            ville=RechercheVille(nom);
		            }
                 Suppressionville(nom);
                 System.out.println("La Suppression a ete effectue");
	        }
		            
                    break;
		    case 5: System.out.println("-----------------------------------------Lier Deux Villes --------------------------------------------------------------");
		    if(ListeVilles.isEmpty())
	        {
		           System.out.println( "Aucune Ville n'a ete Creee ! " );
		           
	        }
	         else
	        {
	        	 if(ListeVilles.size()<2)
	        	 {
	        		 System.out.println("Il n'y a qu'une seulle Ville Existante !");
	        	 }
	        	 else
	        	 {
	        		 Lier(); 
	        	 }
	        	 
	        }
                            
		            break;
		    case 6:System.out.println("-----------------------------------------Verifier si une Ville est Isolee ------------------------------------------------");
		    	     
		    if(ListeVilles.isEmpty())
			{
				System.out.println("Aucune Ville n'a ete Creee ! ");
			}
			else
			{
				System.out.println("Verifier si "
						         + " une Ville est Isolee :\n"
						         + "Choisisser une Ville :");
				AfficherVilles();
				System.out.println("Entrez le Nom de la Ville Choisie : ");
				nom = saisir.nextLine();
				Ville aff = RechercheVille(nom);
				while(aff==null)
				{
					System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
					nom = saisir.nextLine();
					aff = RechercheVille(nom);
				}
				if(aff.Isolee())
				{
					System.out.println("\n=>La Ville "+nom+" est Isolee .");
				}
				else
				{
					System.out.println("\n=>La Ville "+nom+" n'est pas Isolee .");
				}
				
				
			}break;
			
		    case 7: System.out.println("-----------------------------------------Verifier si une ville mene vers exactement 3 villes -----------------------------------");
		    if(ListeVilles.isEmpty())
	        {
		           System.out.println("Aucune Ville n'a ete Creee ! ");
	        }
	         else
	        {
	        	 System.out.println("Choisissez une Ville  :");
                 
                 AfficherVilles();
                 System.out.println("Introduisez le nom de la ville que vous voulez:");
                 nom=saisir.nextLine();
                 ville=RechercheVille(nom);
                 while (ville==null)
                 {
                 	System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
                 	nom=saisir.nextLine();
                     ville=RechercheVille(nom);
                 }
                 if(ville.TroisVille()) System.out.println("La ville "+nom+" a exactement 3 voisines.");
                 else System.out.println("La ville "+nom+" n'a pas exactement 3 voisines.");
	        }
		            
                    break;
		    case 8:System.out.println("-------------------------------Verifier si toutes les Voisines d'une Ville sont de types differents-------------------------------------");
		           
		    if(ListeVilles.isEmpty())
	        {
		           System.out.println("Aucune Ville n'a ete Creee ! ");
	        }
	         else
	        {
	        	 System.out.println("Choisissez une Ville  :");
                 AfficherVilles();
                 System.out.println("Introduisez le nom de la ville que vous voulez:");
                 nom=saisir.nextLine();
                 ville=RechercheVille(nom);
                 while (ville==null)
                 {
                  	System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
                  	nom=saisir.nextLine();
                      ville=RechercheVille(nom);
                 }
                if(ville.TypeVille()) System.out.println("La ville "+nom+" est de type different de celui de ses voisines.");
                else System.out.println("La ville "+nom+" n'est pas de type differents de celui de ses voisines .");
                
	        }
                  break;
		    case 9:System.out.println("-------------------------------Plus Fleurie-------------------------------------------------------------------------------");
                if(ListeVilles.isEmpty())
		        {
			           System.out.println("Aucune Ville n'a ete Creee ! ");
		        }
		         else
		        {
		        	 System.out.println("Choisissez une Ville  :");
	                    AfficherVilles();
	                   System.out.println("Introduisez le nom de la ville que vous voulez:");
	                   nom=saisir.nextLine();
	                   ville=RechercheVille(nom);
	                   while (ville==null)
	                   {
	                    	System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
	                    	nom=saisir.nextLine();
	                        ville=RechercheVille(nom);
	                   }
	                   if(ville.PlusFleurie()) System.out.println("La ville "+nom+" est plus fleurie que ses voisines.");
	                   else System.out.println("La ville "+nom+" n'est pas plus fleurie que ses voisines .");
	                   
 
		        }
		         break;
		    case 10: System.out.println("-----------------------------------------------Verifier Si une Villes est Verte , Ordinaire et etourée de Villes Rouges----------------------------------------------------------");
		     
		    if(ListeVilles.isEmpty())
		        {
			           System.out.println("Aucune Ville n'a ete Creee ! ");
		        }
		         else
		        {
                     System.out.println("Choisissez une Ville  :");
                     
                     AfficherVilles();  
		             System.out.println("Introduisez le nom de la ville que vous voulez:");
                     nom=saisir.nextLine();
                     ville=RechercheVille(nom);
                     while (ville==null)
                    {
                    	System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
                    	nom=saisir.nextLine();
                        ville=RechercheVille(nom);
                     }
                     if(ville.TypeVille()) System.out.println("La ville "+nom+" est verte, a un type non ordinaire et  n’est entourée que par des villes rouges.");
                     else System.out.println("La ville "+nom+" ne confirme pas les caracteristiques.");
                   
		        }
                     break;
              case 11: System.out.println("--------------------------------------------Existence d'un chemin passant de V1,V2,...,Vn----------------------------------------");
		    	     ArrayList <Ville> Ensemble=new ArrayList<Ville> ();
		             
		    	     if(ListeVilles.isEmpty())
	  			        {
	  				           System.out.println("Aucune Ville n'a ete Creee ! ");
	  			        }
	  			         else
	  			        {
	  			           if(ListeVilles.size()<2)
	  			           {
	  			        	 System.out.println("Il n'y a qu'une seulle Ville Creee !");
	  			            }
	  			           else
	  			           {
	  			    	     System.out.println("Choisissez une Ville  :");
                             AfficherVilles();
		             int i=0;
		             System.out.println("Veillez entrer le nom des villes par ordre. Entrez 'Valider' pour voir le resultat");
		             System.out.println("Entrer la "+(i+1)+" ville:");
		             nom=saisir.nextLine();
		             ville=RechercheVille(nom);
		             
		             while(!nom.equalsIgnoreCase("Valider"))
		             {
		            	 ville=RechercheVille(nom);
		            	 boolean b=false;
		            	 if(i>0) {b=ville==Ensemble.get(i-1);}
		            	 while (ville==null || b)
		                   {
		                    	if (ville==null) System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
		                    	if (b) System.out.println("Ville Invalide ! Veuillez Reessayez svp :");
		                    	nom=saisir.nextLine();
			                    ville=RechercheVille(nom);
		                   }
		            	 Ensemble.add(ville);
		            	 i++;
		            	 System.out.println("Entrer la "+(i+1)+" ville (ou /'Valider'):");
		            	 nom=saisir.nextLine();
		             }
		            if (Ville.CheminDirect(Ensemble) && Ensemble.size()>1) System.out.println("Il existe un chemin entre les villes saisies ");
		            else  System.out.println("Il n'existe pas un chemin entre les villes saisies ");
		            
	  			
	  			           }
	  			        }
		    	    
		            break;
		    case 12: System.out.println("--------------------------------------------Existence d'un chemin entre deux villes----------------------------------------");
		    	    
		    if(ListeVilles.isEmpty())
		        {
			           System.out.println("Aucune Ville n'a ete Creee ! ");
		        }
		         else
		        {
		        	 if(ListeVilles.size()<2)
		        	 {
		        		 System.out.println("Il n'y a qu'une seulle Ville Creee!");
		        	 }
		        	 else
		        	 {
		        		 System.out.println("Verifier L'existence d'un Chein entre 2 Ville :");
		 				//-------------------------Saisie des 2 villes à liee
		 				System.out.println("Choisissez Les 2  Villes que vous voulez Lier :");
		 				AfficherVilles();
		 			    //---------------------Ville de DEPART----------------------------------------
		 				System.out.print("Entrez la Ville de Depart : ");
		 				 nom = saisir.nextLine();Ville depart = RechercheVille(nom);
		 				while(depart==null)
		 				{			System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
		 					nom = saisir.nextLine();
		 					depart = RechercheVille(nom);	}
		 				//--------------------------VILLE d'ARRIVEE------------------------------------
		 				System.out.print("Entrez la Ville d'arrivee : ");
		 				nom = saisir.nextLine();Ville arrivee = RechercheVille(nom);
		 				while(arrivee==null ||arrivee == depart )
		 				{
		 					if(arrivee == depart)
		 		      	  {  System.out.println("Liaison Invalide ! Reessayez svp .");    	  }
		 		      	  else
		 		      	  {	System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");   }
		 					nom = saisir.nextLine();
		 					arrivee = RechercheVille(nom);
		 				}
		 				
		 				if(depart.CheminDestination(arrivee))
		 				{
		 					System.out.println("\n=>Il Existe un Chemin de "+
		 				                       depart.getNom()+" Vers "+arrivee.getNom()+" !");
		 				}
		 				else
		 				{
		 					System.out.println("\n=>Il N'Existe PAS un Chemin de "+
		 		                       depart.getNom()+" Vers "+arrivee.getNom()+" !");
		 		
		 				}
			         }
	           	        
		        }
		        	 
           	        break;

		    case 13: System.out.println("-------------------------------------------AFFICHAGE DES INFORMTIONS D'UNE VILLE---------------------------------------");
	           if(ListeVilles.isEmpty())
				        {
					           System.out.println("Aucune Ville n'a ete Creee ! ");
				        }
				         else
				        {
				        	 System.out.println("Choisissez une Ville  :");
		                     AfficherVilles();  
		                     System.out.println("Introduisez le nom de la ville que vous voulez:");
		                     nom=saisir.nextLine();
		                     ville=RechercheVille(nom);
		                     while (ville==null)
		                     {
		                        	System.out.println("Ville Introuvable ! Veuillez Reessayez svp :");
		                         	nom=saisir.nextLine();
		                            ville=RechercheVille(nom);
		                     }
		                     ville.AfficherVille();
		                     
				        }
			         break;
		    case 14:System.out.println("-------------------------------------------AFFICHAGE DE LA LISTE DES VILLES----------------------------------------------");
		    if(ListeVilles.isEmpty())
		        {
			           System.out.println("Aucune Ville n'a ete Creee ! ");
		        }
		         else
		        {
			            System.out.println("Les villes que vous avez cree sont :");
			            AfficherVilles();  
		        }
	            break;
		    case 15: System.out.println("--------------------------------------RESEAUX ROUTIER------------------------------------------------------------------");
		            if(ListeVilles.isEmpty()) System.out.println("Aucune ville n'a ete creer.");
		            else ReseauxRoutier();
		            break;
		    case 16:System.out.println("---------------------------------------AFFICHAGE DES VILLES PAR COULEURS-------------------------------------------------");
		    if(ListeVilles.isEmpty())
	        {
		           System.out.println("Aucune Ville n'a ete Creee ! ");
	        }
	         else
	        {
	        	 VilleCouleur();
	        }
		     break;
		    
		    case 17:System.out.println("--------------------------------------AFFICHGE DES COMPOSANTES FORTEMENT CONNEXES-----------------------------------------");
		    if(ListeVilles.isEmpty())
	        {
		           System.out.println("Aucune Ville n'a ete Creee ! ");
	        }
	         else
	        {
	        	 AfficherComposantes ();
	        }   
	        
            break;
                    
		    case 18:System.out.println("Au revoir !");
		    	   quitter=true;
		    	   break;
		    	   
		     
		    }
		    
		}
	}
}
	
	
	
