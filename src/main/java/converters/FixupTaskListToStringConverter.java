
package converters;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.FixupTask;

@Transactional
@Component
public class FixupTaskListToStringConverter implements Converter<List<FixupTask>, String> {

	@Override
	public String convert(List<FixupTask> list) {
		String res = "";

		//		res = String.valueOf(law.getId());
		if (list == null || list.isEmpty()) {
			res = null;
		} else {
			Assert.isInstanceOf(FixupTask.class, list.get(0), "Object is not of type domain.FixupTask");
			for (int i = 0; i < list.size(); i++) {
				res = res + String.valueOf(list.get(i).getId());
				if (i < list.size() - 1) {
					res = res + ",";
				}
			}
		}

		return res;
	}

}
