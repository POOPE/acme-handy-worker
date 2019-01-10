
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import services.FixupTaskService;
import domain.FixupTask;

@Transactional
@Component
public class StringToFixupTaskConverter implements Converter<String, FixupTask> {

	@Autowired
	private FixupTaskService	fixupTaskService;


	@Override
	public FixupTask convert(String text) {
		FixupTask res;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				res = null;
			} else {
				id = Integer.valueOf(text);
				res = this.fixupTaskService.findById(id);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return res;
	}

}
