-- Désactiver les contraintes des clés étrangères avant de procéder à la suppression
-- car certaines tables ont des FOREIGN KEY avec d'autres tables.
SET REFERENTIAL_INTEGRITY = FALSE;

-- Supprimer les données des tables avant de réinserer
-- les données pour éviter des conflits

DELETE FROM gm_user;
DELETE FROM gm_entreprise;
DELETE FROM gm_facture;
DELETE FROM gm_employe;
DELETE FROM gm_modele;
DELETE FROM gm_poste;
DELETE FROM gm_moto;
DELETE FROM gm_fournisseur;
DELETE FROM gm_genre;
DELETE FROM gm_client;

-- Réactiver la verification des contraintes des clés étrangères après suppression des données.
SET REFERENTIAL_INTEGRITY = TRUE;
