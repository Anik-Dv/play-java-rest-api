package filters;

import play.mvc.EssentialFilter;
import play.filters.cors.CORSFilter;
import play.http.HttpFilters;

import javax.inject.Inject;
import java.util.List;

public class Filters implements HttpFilters {

    @Inject
    CORSFilter corsFilter;


    @Override
    public List<EssentialFilter> getFilters() {
        return List.of(new EssentialFilter[]{corsFilter.asJava()});
    }
}
