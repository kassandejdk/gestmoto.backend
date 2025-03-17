-- Quelques données de test

INSERT INTO gm_entreprise (id, denomination, adresse, logo_url, telephone, ifu, rccm, statut)
VALUES  ('entreprise1', 'General Motors ', 'Ouaga,sect.21', 'https://example.com/logo1.png', '+22611223344', '1234567890123', 'RCCM-123456','ACTIF'),
        ('entreprise2', 'La maison des motos', 'Ouaga,sect.22', 'https://example.com/logo2.png', '+22611223344', '9876543210987', 'RCCM-654321','ACTIF'),
        ('entreprise3', 'Moto House ', 'Ouaga,sect.23', 'https://example.com/logo3.png', '+22611223344', '4567891234567', 'RCCM-789123','ACTIF');

INSERT INTO gm_genre(id, libelle, statut, entreprise)
VALUES ('genre1', 'MOTOCYCLE', 'ACTIF', 'entreprise1');

INSERT INTO gm_modele(id, libelle, statut, entreprise)
VALUES ('modele1', 'MODELE', 'ACTIF', 'entreprise1');

INSERT INTO gm_user(id, nom, prenom, adresse, telephone, email, password, statut, entreprise)
VALUES ('user1', 'KASSANDE', 'Judicael', 'Bobo-Dioulasso, sect.29', '+22674415998','jdkasdel@gmail.com','test@test','ACTIF','entreprise1');

INSERT INTO gm_poste(id, libelle, statut, entreprise)
VALUES ('poste1', 'DG', 'ACTIF', 'entreprise1');

INSERT INTO gm_client(id, profession, ville, province, utilisateur, statut, entreprise)
VALUES ('client1', 'Commercant', 'Ouaga', 'KADIOGO','user1','ACTIF','entreprise1');

INSERT INTO gm_employe(id, salaire, poste,  utilisateur, statut, entreprise)
VALUES ('employe1', 120000, 'poste1', 'user1','ACTIF','entreprise1');

INSERT INTO gm_fournisseur(id, utilisateur, statut, entreprise)
VALUES ('fournisseur1', 'user1','ACTIF','entreprise1');

INSERT INTO gm_moto(id, prix, charge_utile, capacite, carosserie, marque, type, etat, immatriculation, numero_serie, fournisseur, statut, entreprise)
VALUES('moto1', 178999, 3445, 34, 'carosserie', 'YAMAHA', '122', 'NEUF', 'imma-rccc','numero-serie-332f','fournisseur1', 'ACTIF', 'entreprise1');

INSERT INTO gm_facture(id, entreprise, client, moto, statut)
VALUES('facture1','entreprise1','client1','moto1','ACTIF');