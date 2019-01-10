
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import services.HandyWorkerService;
import domain.HandyWorker;

@Transactional
@Component
public class StringToHandyWorkerConverter implements Converter<String, HandyWorker> {

	@Autowired
	private HandyWorkerService	handyWorkerService;


	@Override
	public HandyWorker convert(String text) {
		HandyWorker res;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				res = null;
			} else {
				id = Integer.valueOf(text);
				res = this.handyWorkerService.findOne(id);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return res;
	}

}
