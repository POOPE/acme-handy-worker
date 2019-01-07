
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import services.ActorService;
import domain.Actor;

@Transactional
@Component
public class StringToActorConverter implements Converter<String, Actor> {

	@Autowired
	private ActorService	actorService;


	@Override
	public Actor convert(String text) {
		Actor res;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				res = null;
			} else {
				id = Integer.valueOf(text);
				res = this.actorService.findById(id);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return res;
	}

}
