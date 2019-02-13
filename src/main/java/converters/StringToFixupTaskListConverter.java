
package converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import services.FixupTaskService;
import domain.FixupTask;

@Transactional
@Component
public class StringToFixupTaskListConverter implements Converter<String, List<FixupTask>> {

	@Autowired
	private FixupTaskService	fixupTaskService;


	@Override
	public List<FixupTask> convert(String text) {
		List<FixupTask> res = new ArrayList<>();

		try {
			if (StringUtils.isEmpty(text)) {
				//do nothing
			} else {
				String ids[] = text.split(",");
				for (int i = 0; i < ids.length; i++) {
					FixupTask fixupTask = this.fixupTaskService.findById(Integer.valueOf(ids[i].trim()));
					res.add(fixupTask);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return res;
	}

}
