package de.ksmwsk.deleteall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

public class ResultsetSpliterator extends Spliterators.AbstractSpliterator<Map<String, Object>> {

    private static final Logger LOG = LoggerFactory.getLogger(ResultsetSpliterator.class);

    private final ResultSet resultSet;

    public ResultsetSpliterator(final ResultSet resultSet){
        super(Long.MAX_VALUE, Spliterator.ORDERED);
        this.resultSet = resultSet;
    }

    @Override
    public boolean tryAdvance(Consumer action) {
        if (next()) {
            try {
                action.accept(getMap());
            } catch (SQLException e) {
                LOG.warn("Sql Exception", e);
            }
            return true;
        } else {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOG.warn("Sql Exception", e);
            }
            return false;
        }
    }

    private boolean next() {
        try {
            return resultSet.next();
        } catch (Exception e) {
            LOG.warn("Exception on next", e);
            throw new RuntimeException(e);
        }
    }

    private Map<String, Object> getMap() throws SQLException {
        Map<String, Object> record = new HashMap<>();
        for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
            record.put(resultSet.getMetaData().getColumnName(i + 1), resultSet.getObject(i + 1));
        }
        return record;
    }
}
