package br.com.ifreire.daos;

import org.springframework.beans.factory.annotation.Autowired;
import com.db4o.ObjectContainer;
import com.db4o.ext.Db4oIOException;

import br.com.caelum.vraptor.ioc.Component;
import br.com.ifreire.db.Db4oContext;

@Component
public class DAO implements IDAO
{
	@Autowired(required = true)
    protected Db4oContext db4o;
	protected ObjectContainer container;
	
	public DAO()
	{
		db4o = new Db4oContext();
		container = db4o.objectContainer();
	}

	public void openContainer()
	{
		try
		{
			container.ext().openSession();
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public void closeContainer()
	{
		try
		{
			container.close();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public void store(Object obj)
	{
		try
		{
			openContainer();
			
			container.store(obj);
			container.commit();
		}
		catch (Exception e)
		{
			throw e;
		}
//		finally
//		{
//			closeContainer();
//		}
	}
	
	public void delete(Object obj)
	{
		try
		{
			openContainer();
			
			container.delete(obj);
		}
		catch(Db4oIOException e)
		{
			throw e;
		}
//		finally
//		{
//			closeContainer();
//		}
	}
}