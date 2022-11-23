---
foam_template: # this is a YAML "Block" mapping ("Flow" mappings aren't supported)
  name: Diary Note Template # Attributes must be on the lines immediately following `foam_template`
  description: This is diary note template
  filepath: "information/diary/${FOAM_DATE_YEAR/년//gi}/${FOAM_DATE_MONTH/월//gi}/${FOAM_DATE_YEAR/년//gi}-${FOAM_DATE_MONTH/월//gi}-${FOAM_DATE_DATE/일//gi}_${FOAM_TITLE}.md"
  # filepath: "diary/daily-diary-${FOAM_DATE_YEAR}-${FOAM_DATE_MONTH}-${FOAM_DATE_DATE}.md"
---

<!-- The actual contents of the template begin after the `---` thematic break immediately below this line-->
---
created: ${FOAM_DATE_YEAR/년//gi}-${FOAM_DATE_MONTH/월//gi}-${FOAM_DATE_DATE/일//gi}T${FOAM_DATE_HOUR}:${FOAM_DATE_MINUTE}:${FOAM_DATE_SECOND}
tags: []
---

# ${FOAM_TITLE}
