package pt.gongas.customitems.shared.util.config;

public interface ConfigurationAdapter<T,S> {

    T adapt(S section);

}
