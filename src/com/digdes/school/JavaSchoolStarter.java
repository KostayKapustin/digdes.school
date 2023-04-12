package com.digdes.school;

import java.util.*;
import java.util.regex.Pattern;

public class JavaSchoolStarter {

    private final List<Map<String, Object>> data = new ArrayList<>();
    private Long id;
    private String lastName;
    private Long age;
    private Double cost;
    private Boolean active;

    public JavaSchoolStarter() {

    }

    public List<Map<String, Object>> execute(String request) throws Exception {
        String requestToUpperCase = request.toUpperCase();
        String[] team = requestToUpperCase.split(" ");
        String dataWithoutSpaces = request.replaceAll("\\s+", "").replaceAll("'", "").
                replaceAll("%", "");
        String[] commaSeparatedData = dataWithoutSpaces.split(",");
        if(!team[0].equals("SELECT")) {
            validationColumnNames(request, dataWithoutSpaces);
        }
        switch (team[0]) {
            case "INSERT" -> insert(dataWithoutSpaces, commaSeparatedData);
            case "UPDATE" -> update(dataWithoutSpaces, requestToUpperCase);
            case "DELETE" -> delete(dataWithoutSpaces, commaSeparatedData);
            case "SELECT" -> {
                return select(dataWithoutSpaces);
            }
        }
        return data;
    }

    private void insert(String dataWithoutSpaces, String[] commaSeparatedData) {
        Map<String, Object> row = new HashMap<>();
        for (var cSData : commaSeparatedData) {

            String meaning = cSData.substring(cSData.indexOf("=") + 1);

            if (cSData.toUpperCase().contains("ID")) {
                checkingIdNull(meaning);
            } else if (!dataWithoutSpaces.toUpperCase().contains("ID")) {
                id = null;
            }

            if (cSData.toUpperCase().contains("LASTNAME")) {
                lastName = meaning;
            } else if (!dataWithoutSpaces.toUpperCase().contains("LASTNAME")) {
                lastName = null;
            }

            if (cSData.toUpperCase().contains("AGE")) {
                checkingAgeNull(meaning);
            } else if (!dataWithoutSpaces.toUpperCase().contains("AGE")) {
                age = null;
            }

            if (cSData.toUpperCase().contains("COST")) {
                checkingCostNull(meaning);
            } else if (!dataWithoutSpaces.toUpperCase().contains("COST")) {
                cost = null;
            }

            if (cSData.toUpperCase().contains("ACTIVE")) {
                checkingActiveNull(meaning);
            } else if (!dataWithoutSpaces.toUpperCase().contains("ACTIVE")) {
                active = null;
            }
        }
        if (id == null && lastName == null && age == null && active == null && cost == null) {
            throw new NullPointerException("ƒанные путы!");
        } else {
            row.put("id", id);
            row.put("lastName", lastName);
            row.put("age", age);
            row.put("cost", cost);
            row.put("active", active);
        }
        data.add(row);
    }

    private void update(String dataWithoutSpaces, String requestToUpperCase) {

        String meaning = dataWithoutSpaces.substring(dataWithoutSpaces.indexOf("=") + 1);

        if (!dataWithoutSpaces.toUpperCase().contains("WHERE")) {

            if (dataWithoutSpaces.toUpperCase().contains("ID")) {
                checkingIdNull(meaning);
                for (Map<String, Object> datum : data) {
                    datum.put("id", id);
                }
            }

            if (dataWithoutSpaces.toUpperCase().contains("LASTNAME")) {
                lastName = meaning;
                for (Map<String, Object> datum : data) {
                    datum.put("lastName", lastName);
                }
            }

            if (dataWithoutSpaces.toUpperCase().contains("AGE")) {
                checkingAgeNull(meaning);
                for (Map<String, Object> datum : data) {
                    datum.put("age", age);
                }
            }

            if (dataWithoutSpaces.toUpperCase().contains("COST")) {
                checkingCostNull(meaning);
                for (Map<String, Object> datum : data) {
                    datum.put("cost", cost);
                }
            }

            if (dataWithoutSpaces.toUpperCase().contains("ACTIVE")) {
                checkingActiveNull(meaning);
                for (Map<String, Object> datum : data) {
                    datum.put("active", active);
                }
            }
        } else {
            String[] values = dataWithoutSpaces.toUpperCase().substring
                    (requestToUpperCase.indexOf("S")).split("WHERE");

            meaning = values[0].substring(values[0].indexOf("=") + 1);

            String meaning1 = values[1].substring(values[1].indexOf("=") + 1);

            if (dataWithoutSpaces.toUpperCase().contains("ID")) {
                for (var map : data) {
                    checkingIdNull(meaning);
                    if (map.get("id") == id) {
                        checkingIdNull(meaning1);
                        map.put("id", id);
                    }
                }
            }

            if (dataWithoutSpaces.toUpperCase().contains("LASTNAME")) {
                for (var map : data) {
                    if (map.get("lastName") == meaning) {
                        map.put("lastName", meaning1);
                    }
                }
            }

            if (dataWithoutSpaces.toUpperCase().contains("AGE")) {
                for (var map : data) {
                    checkingAgeNull(meaning);
                    if (map.get("age") == age) {
                        checkingAgeNull(meaning1);
                        map.put("age", age);
                    }
                }
            }

            if (dataWithoutSpaces.toUpperCase().contains("COST")) {
                for (var map : data) {
                    checkingCostNull(meaning);
                    if (map.get("cost") == cost) {
                        checkingCostNull(meaning1);
                        map.put("cost", cost);
                    }
                }
            }

            if (dataWithoutSpaces.toUpperCase().contains("ACTIVE")) {
                for (var map : data) {
                    checkingActiveNull(meaning);
                    if (map.get("active") == active) {
                        checkingActiveNull(meaning);
                        map.put("active", active);
                    }
                }
            }
        }
    }

    private void delete(String dataWithoutSpaces, String[] commaSeparatedData) {
        if (!dataWithoutSpaces.contains("WHERE")) data.clear();

        List<Map<String, Object>> dataDelete = new ArrayList<>();

        for (var cSData : commaSeparatedData) {
            String meaning = cSData.substring(cSData.indexOf("=") + 1);

            if (cSData.toUpperCase().contains("ID")) {
                checkingIdNull(meaning);
                for (Map<String, Object> datum : data) {
                    if (datum.get("id") == id) dataDelete.add(datum);
                }
            }

            if (cSData.toUpperCase().contains("LASTNAME")) {
                lastName = meaning;
                for (Map<String, Object> datum : data) {
                    if (datum.get("lastName") == lastName) dataDelete.add(datum);
                }
            }

            if (cSData.toUpperCase().contains("AGE")) {
                checkingAgeNull(meaning);
                for (Map<String, Object> datum : data) {
                    if (datum.get("age") == age) dataDelete.add(datum);
                }
            }

            if (cSData.toUpperCase().contains("COST")) {
                checkingCostNull(meaning);
                for (Map<String, Object> datum : data) {
                    if (datum.get("cost") == cost) dataDelete.add(datum);
                }
            }

            if (cSData.toUpperCase().contains("ACTIVE")) {
                checkingActiveNull(meaning);
                for (Map<String, Object> datum : data) {
                    if (datum.get("active") == active) dataDelete.add(datum);
                }
            }
        }
        for (var dataD : dataDelete) {
            data.remove(dataD);
        }
    }

    private List<Map<String, Object>> select(String dataWithoutSpaces) throws Exception {
        List<Map<String, Object>> dataNewSelect = new ArrayList<>();
        if (dataWithoutSpaces.equalsIgnoreCase("SELECT")) {
            dataNewSelect = data;
        } else {
            if (dataWithoutSpaces.toUpperCase().contains("OR") || dataWithoutSpaces.toUpperCase().contains("AND")) {
                dataNewSelect = separationAndOr(dataWithoutSpaces);
            } else {
                if (dataWithoutSpaces.toUpperCase().contains("ID")) {
                    dataNewSelect = definitionLogicalOperator(dataWithoutSpaces, "id");
                }
                if (dataWithoutSpaces.toUpperCase().contains("LASTNAME")) {
                    dataNewSelect = definitionLogicalOperator(dataWithoutSpaces, "lastName");
                }
                if (dataWithoutSpaces.toUpperCase().contains("AGE")) {
                    dataNewSelect = definitionLogicalOperator(dataWithoutSpaces, "age");
                }
                if (dataWithoutSpaces.toUpperCase().contains("COST")) {
                    dataNewSelect = definitionLogicalOperator(dataWithoutSpaces, "cost");
                }
                if (dataWithoutSpaces.toUpperCase().contains("ACTIVE")) {
                    dataNewSelect = definitionLogicalOperator(dataWithoutSpaces, "active");
                }
            }
        }
        return dataNewSelect;
    }

    private List<Map<String, Object>> separationAndOr(String dataWithoutSpaces) throws Exception {
        List<Map<String, Object>> dataNewSelect3 = new ArrayList<>();
        if (dataWithoutSpaces.toUpperCase().contains("OR")) {
            List<Map<String, Object>> dataNewSelect4;
            String[] separationOr = dataWithoutSpaces.toUpperCase().split("OR");
            int i = 0;
            int j = 0;
            for (var separationA : separationOr) {
                j += separationA.length();
                dataNewSelect4 = select(dataWithoutSpaces.substring(i, j));
                dataNewSelect3.addAll(dataNewSelect4);
                i += separationA.length() + 2;
                j += 2;
            }
        } else {
            String[] separationAnd = dataWithoutSpaces.toUpperCase().split("AND");
            List<Map<String, Object>> dataNewSelect;
            List<Map<String, Object>> dataNewSelect2 = new ArrayList<>();
            int i = 0;
            int j = 0;
            for (var separationA : separationAnd) {
                j += separationA.length();
                dataNewSelect = select(dataWithoutSpaces.substring(i, j));
                for (var a : dataNewSelect) {
                    if (!dataNewSelect2.contains(a)) {
                        dataNewSelect2.add(a);
                    } else {
                        dataNewSelect3.add(a);
                        Set<Map<String, Object>> duplicates = new HashSet<>();
                        for (int y = 0; y < dataNewSelect3.size(); y++) {
                            Map<String, Object> e1 = dataNewSelect3.get(y);
                            if (e1 == null) continue;
                            for (int x = 0; x < dataNewSelect3.size(); x++) {
                                if (y == x) continue;
                                Map<String, Object> e2 = dataNewSelect3.get(x);
                                if (e1.equals(e2)) {
                                    duplicates.add(e2);
                                }
                            }
                        }
                        if (duplicates.size() != 0) {
                            dataNewSelect3 = new ArrayList<>(duplicates);
                        }
                    }
                }
                i += separationA.length() + 3;
                j += 3;
            }
        }
        return dataNewSelect3;
    }

    private List<Map<String, Object>> definitionLogicalOperator(String dataWithoutSpaces,
                                                                String nameColumn) throws Exception {
        List<Map<String, Object>> intermediateNewList = new ArrayList<>();
        String operator = dataWithoutSpaces.replaceAll("\\w|\\d", "");
        String meaningString = dataWithoutSpaces.substring(dataWithoutSpaces.indexOf("=") + 1);

        if (dataWithoutSpaces.toUpperCase().contains("LIKE")) {
            String symbolsIlike = dataWithoutSpaces.substring(dataWithoutSpaces.indexOf("ilike") + 5);
            String symbolsLike = dataWithoutSpaces.substring(dataWithoutSpaces.indexOf("like") + 4);
            if (dataWithoutSpaces.toUpperCase().contains("ILIKE")) {
                validatingValidOperationsLike(nameColumn, "ilike", symbolsIlike);
                for (Map<String, Object> datum : data) {
                    if (String.valueOf(datum.get(nameColumn)).toUpperCase().contains(symbolsIlike.toUpperCase())) {
                        intermediateNewList.add(datum);
                    }
                }

            } else {
                validatingValidOperationsLike(nameColumn, "like", symbolsLike);
                for (Map<String, Object> datum : data) {
                    if (String.valueOf(datum.get(nameColumn)).contains(symbolsLike)) {
                        intermediateNewList.add(datum);
                    }
                }
            }
        }
        if (operator.equals("=")) {
            for (Map<String, Object> datum : data) {
                if (String.valueOf(datum.get(nameColumn)).equals(meaningString)) {
                    intermediateNewList.add(datum);
                }
            }
        }
        if (operator.equals("!=")) {
            for (Map<String, Object> datum : data) {
                if (!String.valueOf(datum.get(nameColumn)).equals(meaningString)) {
                    intermediateNewList.add(datum);
                }
            }
        }
        if (operator.equals(">=")) {
            validatingValidOperations(nameColumn, operator, meaningString);
            for (Map<String, Object> datum : data) {
                if (Double.parseDouble(String.valueOf(datum.get(nameColumn))) >= Double.parseDouble
                        (dataWithoutSpaces.substring(dataWithoutSpaces.indexOf("=") + 1))) {
                    intermediateNewList.add(datum);
                }
            }
        }
        if (operator.equals("<=")) {
            validatingValidOperations(nameColumn, operator, meaningString);
            for (Map<String, Object> datum : data) {
                if (Double.parseDouble(String.valueOf(datum.get(nameColumn))) <= Double.parseDouble
                        (dataWithoutSpaces.substring(dataWithoutSpaces.indexOf("=") + 1))) {
                    intermediateNewList.add(datum);
                }
            }
        }
        if (operator.equals("<")) {
            validatingValidOperations(nameColumn, operator, meaningString);
            for (Map<String, Object> datum : data) {
                if (Double.parseDouble(String.valueOf(datum.get(nameColumn))) < Double.parseDouble
                        (dataWithoutSpaces.substring(dataWithoutSpaces.indexOf("=") + 1))) {
                    intermediateNewList.add(datum);
                }
            }
        }
        if (operator.equals(">")) {
            validatingValidOperations(nameColumn, operator, meaningString);
            for (Map<String, Object> datum : data) {
                if (Double.parseDouble(String.valueOf(datum.get(nameColumn))) < Double.parseDouble
                        (dataWithoutSpaces.substring(dataWithoutSpaces.indexOf("=") + 1))) {
                    intermediateNewList.add(datum);
                }
            }
        }
        return intermediateNewList;
    }

    private void checkingIdNull(String meaning) {
        if (meaning.equalsIgnoreCase("NULL")) {
            id = null;
        } else {
            id = Long.valueOf(meaning);
        }
    }

    private void checkingAgeNull(String meaning) {
        if (meaning.equalsIgnoreCase("NULL")) {
            age = null;
        } else {
            age = Long.valueOf(meaning);
        }
    }

    private void checkingCostNull(String meaning) {
        if (meaning.equalsIgnoreCase("NULL")) {
            cost = null;
        } else {
            cost = Double.valueOf(meaning);
        }
    }

    private void checkingActiveNull(String meaning) {
        if (meaning.equalsIgnoreCase("NULL")) {
            active = null;
        } else {
            active = Boolean.valueOf(meaning);
        }
    }

    private void validatingValidOperations(String nameColumn, String operator, String meaningString) throws Exception {
        if (nameColumn.equalsIgnoreCase("LASTNAME") || nameColumn.equalsIgnoreCase("ACTIVE")) {
            throw new Exception("С" + nameColumn + "С" + operator + meaningString);
        }
    }

    private void validatingValidOperationsLike
            (String nameColumn, String operator, String meaningString) throws Exception {
        if (nameColumn.equalsIgnoreCase("ID") ||
                nameColumn.equalsIgnoreCase("AGE") ||
                nameColumn.equalsIgnoreCase("COST") ||
                nameColumn.equalsIgnoreCase("ACTIVE")) {
            throw new Exception("С" + nameColumn + "С " + operator + " " + meaningString);
        }
    }

    private void validationColumnNames(String request, String dataWithoutSpaces) throws Exception {
        StringBuilder extraColumns = new StringBuilder();
        String toUpperCaseRequest = request.toUpperCase();
        Pattern pattern = Pattern.compile("'(.*?)'");
        List<String> matcher = pattern.matcher(toUpperCaseRequest)
                .results()
                .map(mr -> mr.group(1)).toList();
        ArrayList<String> columns = new ArrayList<>(matcher);
        String[] commaSeparatedString = dataWithoutSpaces.toUpperCase().split(",");

        if (request.toUpperCase().contains("WHERE")) {
            String[] myColumns = new String[]{"ID", "LASTNAME", "AGE", "COST", "ACTIVE"};
            if (columns.size() == 4) {
                columns.remove(1);
                columns.remove(columns.size() - 1);
            }
            for (var myColumn : myColumns) {
                if (columns.size() != 0) {
                    if (columns.get(0).contains(myColumn)) {
                        columns.remove(0);
                    }
                    if (columns.get(columns.size() - 1).contains(myColumn)) {
                        columns.remove(columns.size() - 1);
                    }
                }
            }
        } else {
            for (var cSString : commaSeparatedString) {
                String[] stringSeparatedEqual = cSString.split("=");
                for (int i = 0; i < stringSeparatedEqual.length; i++) {
                    i++;
                    columns.remove(stringSeparatedEqual[i]);
                }
            }
            columns.remove("ID");
            columns.remove("LASTNAME");
            columns.remove("AGE");
            columns.remove("COST");
            columns.remove("ACTIVE");
        }
        if (columns.size() != 0) {
            for (var column : columns) {
                extraColumns.append(request, toUpperCaseRequest.indexOf(column),
                        toUpperCaseRequest.indexOf(column) + column.length()).append(" ");
            }
            throw new Exception("Ћишние колонки: " + extraColumns);
        }
    }
}
