package br.com.church.facade;

public interface Facade<T> {
	public Result<T> validate(T obj);
}
