package Package;

import java.util.ArrayList;

public class Ville {
  
	//Attributs 
	
  private String nom, wilaya;
  private int nbreHabit;
  private float superficie;
  private String classement;
  private String type;
  private String couleur;
  
  private static int cpt=0; 
  private final int numerotation;
  private static int cptpartie=0;
  
  //Tableaux des types et des classements possibles
  private final static String[] Types= {"Agricole","Touristique", "Industrielle","Ordinnaire"};
  private final static String[] Classements= {"Tres fleurie","Bien fleurie","Assez fleurie","Peu fleurie","Pas du tout fleurie"}; 
  //Tableaux des voisines 
  private ArrayList<Ville> ListeSucc= new ArrayList <Ville> ();
  private ArrayList<Ville> ListePred= new ArrayList <Ville> ();
  
  //Tableau utiliser pour trouver le chemin entre deux villes
  private static ArrayList<Ville> VilleParcourues =new ArrayList <Ville> ();
  
  //Pour le partitionnement
  private boolean plus;
  private boolean moins;
  private int partie;  
  
  // 1-Constructeur
  public Ville (String nom, String wilaya,int nbreHabit, float superficie, int type,int classement) 
  {
	
	  this.nom=nom;
	  this.wilaya=wilaya;
	  this.nbreHabit=nbreHabit;
	  this.superficie=superficie;
	  this.type=Types[type];
	  this.classement=Classements[classement];
	  cpt++;
	  this.numerotation=cpt;
	  this.setCouleur();
  }

//Affichage d'une ville
public void AfficherVille ()
{
	System.out.println("-Le nom de la ville :"+this.nom);
	System.out.println("-Le wilaya :"+this.wilaya);
	System.out.println("-Le nombre d'habitant :"+this.nbreHabit);
	System.out.println("-La superficie :"+this.superficie);
	System.out.println("-Le type :"+this.type);
	System.out.println("-Le classement :"+this.classement);
	System.out.println("-La couleur :"+this.couleur);
	System.out.println("-La numerotation :"+this.numerotation);
}


  //  2- Setters and guetters 
  
public String getNom() {
	return nom;
}

public int getNumerotation() {
	return numerotation;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getWilaya() {
	return wilaya;
}

public void setWilaya(String wilaya) {
	this.wilaya = wilaya;
}

public int getNbreHabit() {
	return nbreHabit;
}

public void setNbreHabit(int nbreHabit) {
	this.nbreHabit = nbreHabit;
}

public float getSuperficie() {
	return superficie;
}

public void setSuperficie(float superficie) {
	this.superficie = superficie;
}

public String getType() {
	return type;
}

public void setType(int type) {
	this.type = Types[type];
}

public String getClassement() {
	return classement;
}

public void setClassement(int classement) {
	this.classement = Classements[classement];
}
public String getCouleur() {
	return couleur;
}

public ArrayList<Ville> getListeSucc() {
	return ListeSucc;
}

public ArrayList<Ville> getListePred() {
	return ListePred;
}

// 3-Methode couleur: attribut une couleur a la ville

public void setCouleur() 
{
	if((this.type.equals(Types[3])) && 
       (this.classement.equals(Classements[3]) || 
		this.classement.equals(Classements[4])))
    	{
		this.couleur="Rouge" ;
    	}
	else
	{
		if((this.type.equals(Types[3]) && !this.classement.equals(Classements[4])) || 
	            (!this.type.equals(Types[3]) && (this.type.equals(Types[2]) ||
	            this.type.equals(Types[3]))))
        {
	        	this.couleur = "Orange" ; 
	    }
		else
		{
			this.couleur = "Verte";
		}
	}
	
}
public boolean isPlus() {
	return plus;
}

public void setPlus(boolean plus) {
	this.plus = plus;
}

public boolean isMoins() {
	return moins;
}

public void setMoins(boolean moins) {
	this.moins = moins;
}


public int getPartie() {
	return partie;
}

public void setPartie() {
	
	
	this.partie = cptpartie;
	
}


// ******************************* Methodes d'instances ***************************************************** 
  
 
 // 4- Verifier si cette ville n'a pas de voisines
  public boolean Isolee()
  {
		return this.ListeSucc.size()==0 ;
  }
  
  //5- Verifier si cette ville na que 3 voisines
 public boolean TroisVille()
  {
		return this.ListeSucc.size()==3;
  }

 //6- Verifier si le type de cette ville est differents du type de ces voisines
 
 public boolean TypeVille()
  {
	for(Ville V : this.ListeSucc)
	{
	    if(this.type.equals(V.type))
	    	return false;
	}
	
	for(Ville V : this.ListePred)
	{
	    if(this.type.equals(V.type))
	    	return false;
	}
		return true ;
	}
	
 
 //7-Verifier si cette ville est plus fleurie que ces voisines
 
  public boolean PlusFleurie ()
  {
	  if (this.classement.equals("Pas du tout fleurie")) return false; 
	  
	  for (Ville v :this.ListeSucc)
	  {
		  if (v!=null)
		  {
			  if (!this.MieuxClassees(v)) return false;
		  }
	  }
	  for (Ville v :this.ListePred)
	  {
		  if (v!=null)
		  {
			  if (!this.MieuxClassees(v)) return false;
		  }
	  }
	  return true;
  }
  //8- Tester si la ville est verte non ordinaire et n'est entouree que par des villes rouges
  
  public boolean TesterVille(ArrayList<Ville> Villes)
  {
	  return (this.couleur.equalsIgnoreCase("Verte") && this.type.equalsIgnoreCase("Ordinaire") && EntoureeRouge()); 
  }
 
  // 9- Verifier s'il existe un chemin directe 
  
  public static boolean CheminDirect(ArrayList <Ville> Ensemble)
  {
	  for (int i=0; i<Ensemble.size()-1;i++)
	  {
			  if (!Ensemble.get(i).VoisineSucc(Ensemble.get(i+1)))
				  return false;
	  }
	  return true;
  }
  
  
//10- Verifier s'il existe un chemin entre cette ville et une ville destination
  public boolean VerifierChemin(Ville Destination)
  {
	  
	  
	  if (!VilleParcourues.contains(this)) VilleParcourues.add(this);   // Si la ville n'a pas ete parcourue on la rajoute au tableau des villes parcourues
	          else return false;      // Si la ville a ete parcourue
	  if (this.VoisineSucc(Destination)) return true;   // Ville destination est voisine de la ville courante
	  if (this.ListeSucc.isEmpty()) return false;    //La ville n'a pas de successeurs
	  
	  
	  for (int i=0; i<this.ListeSucc.size();)
	  {
		  if(!this.ListeSucc.get(i).VerifierChemin(Destination)) i++; //Si le chemin n'a pas ete trouve a partir de la i-eme voisine on passe a la suivante
		  else return true; //Si le chemin a ete trouve on retourne true
	  }
	  return false; // Si aucun chemin n'a ete trouve on retourne false
  }
  
  // 10- Vider le tableau des villes deja parcourues
  public void ViderTableau()
  {
	  VilleParcourues.removeAll(VilleParcourues); 
  }
  
  // 10- Methode de recherche de chemin complete 
  public boolean CheminDestination (Ville destination)
  {
	  boolean existe= this.VerifierChemin(destination); //verification de l'existence du chemin 
	  this.ViderTableau(); //Vider le tableau statique des villes parcourues
	  return existe;
  }
  
//15- THG :Marquer les descendants de cette ville par un plus (+)
 public void Successeur()
	{
		this.plus=true;
		for(int i = 0 ; i<this.ListeSucc.size();)
		{
			if(!this.ListeSucc.get(i).isPlus())
			{
				this.ListeSucc.get(i).Successeur();
			}
			i++;
		}
	}
	//15- THG :Marquer les ascendants de cette ville par un moins (-)
	public void Predecesseur()
	{
		this.moins=true;
		for(int i = 0 ; i<this.ListePred.size();)
		{
			if(!this.ListePred.get(i).isMoins())
			{
				this.ListePred.get(i).Predecesseur();
			}
			i++;
		}
	}
  
  //****************************** Methodes Secondaires ********************************************************
	//Comparaison des classements des villes
  private boolean MieuxClassees (Ville voisine)
  {
	  
	  return (IndexClassement (this.classement) < IndexClassement (voisine.classement)) ;
  }
  
  //Retourne l'indice du classement dans le tableau
  public static int IndexClassement (String classement)
  {
	  int i=0;
	  for (i=0; i<Classements.length && !Classements[i].equals(classement) ;i++ );
	  return i;
  }
  
//Retourne l'indice du type dans le tableau
  public static int IndexType (String type)
  {
	  int i=0;
	  for (i=0; i<Types.length && !Types[i].equals(type) ;i++ );
	  return i;
  }
  
  //Voir si les voisines d'une ville sont rouges
  public boolean EntoureeRouge()
	{
		for(int i=0 ; i<this.ListePred.size() ; i++)
		{
			if(!this.ListePred.get(i).couleur.equalsIgnoreCase("Rouge"))
				return false;
		}
		
		for(int i=0 ; i<this.ListeSucc.size() ; i++)
		{
			if(!this.ListeSucc.get(i).couleur.equalsIgnoreCase("Rouge"))
				return false;
		}
		return true;
	}
  
  
  public boolean VoisineSucc(Ville v)
  {
	  for (Ville vi :this.ListeSucc)
	  {
		  if (vi!=null)
		  if(vi==v)
		  return true;
	  }
	  return false;
  }

public boolean VoisinePred(Ville ville)
{
	if (ville.VoisineSucc(this)) return true;
	  return false;
}


//Ajouter un succeseur
public void addSucc(Ville V)
{
	if(!this.ListeSucc.contains(V))
		this.ListeSucc.add(V);
}

//Supprimer un succeseur
public void removeSucc(Ville V)
{
	if(this.ListeSucc.contains(V))
		this.ListeSucc.remove(V);
}

//Ajouter un predecesseur
public void addPred(Ville V)
{
	if(!this.ListePred.contains(V))
		this.ListePred.add(V);
}

//Supprimer un predecesseur
public void removePred(Ville V)
{
	if(this.ListePred.contains(V))
		this.ListePred.remove(V);
}

//Utiliser dans la methodes Modification d'une ville

public  Ville RechercheSucc(String nom)
{
	for(Ville Town : this.ListeSucc )
	{		if(Town.getNom().equalsIgnoreCase(nom));
	        return Town ;
     }		        
	return null ;	
}

//Utiliser dans la methodes Modification d'une ville
public  Ville RecherchePred(String nom)
{
	for(Ville Town : this.ListePred )
	{		if(Town.getNom().equalsIgnoreCase(nom));
	        return Town ;
     }		        
	return null ;	
}

//Utiliser dans la methode Partitionnement
public static void IncPartie()
{
	cptpartie++;
}
}