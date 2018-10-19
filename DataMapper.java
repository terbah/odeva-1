package coo.fil.org.persistance;

import coo.fil.org.domain.Contact;
import coo.fil.org.error.ContactNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataMapper
{
	System.out.println("Hello World !");
    public static synchronized Contact find(int id_contact) throws ContactNotFoundException
    {
        //String url = "jdbc:oracle:fil.univ-lille1.fr:1s21:filora (mdp oracle)";
        Connection conn = getConnection();
        try
        {
            PreparedStatement ps = conn.prepareStatement("select id, nom, prenom, numero from contact where id = ?");
            ps.setInt(1, id_contact);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                Contact co = new Contact(rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("numero"));
                return co;
            } else
            {
                throw new ContactNotFoundException();
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            Disconnect(conn);
        }
        throw new ContactNotFoundException();
    }

    public static synchronized List<Contact> findAll()
    {
        List<Contact> contacts = new ArrayList<>();
        Connection c = getConnection();
        try
        {
            PreparedStatement stmt = c.prepareStatement("select id, nom, prenom, numero from contact");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                contacts.add(new Contact(rs.getInt("id"), rs.getString("nom"),
                        rs.getString("prenom"), rs.getString("numero")));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return contacts;
    }

    public static synchronized void insert(Contact contact)
    {
        Connection conn = getConnection();
        try
        {
            PreparedStatement ps = conn.prepareStatement("insert into contact values(?,?,?,?)");
            ps.setInt(1, contact.getId());
            ps.setString(2, contact.getNom());
            ps.setString(3, contact.getPrenom());
            ps.setString(4, contact.getNumero_telephone());
            ps.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            Disconnect(conn);
        }
    }


    public static synchronized void update(int id, Contact contact)
    {
        Connection conn = getConnection();
        try
        {
            PreparedStatement ps = conn.prepareStatement("update contact set nom = ?, prenom = ?, numero = ? where id = ?");
            ps.setString(1, contact.getNom());
            ps.setString(2, contact.getPrenom());
            ps.setString(3, contact.getNumero_telephone());
            ps.setInt(4, contact.getId());
            ps.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            Disconnect(conn);
        }
    }

    public static synchronized void remove(int id)
    {

        Connection conn = getConnection();
        try
        {
            PreparedStatement ps = conn.prepareStatement("delete from contact where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            Disconnect(conn);
        }
    }


    public static Connection getConnection()
    {
        try
        {
            return DriverManager.getConnection(getConnectionUrl());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void Disconnect(Connection con)
    {
        try
        {
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static String getConnectionUrl()
    {
        return "jdbc:sqlite:" + System.getProperty("user.home") + System.getProperty("file.separator") + "bbdb.db";
    }

    public static synchronized List<Contact> findFriendList(int id)
    {

    }
}
