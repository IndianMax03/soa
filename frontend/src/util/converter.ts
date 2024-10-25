export function xml2json(srcDOM) {
  let children = [...srcDOM.children];

  if (!children.length) {
    return srcDOM.innerHTML;
  }

  let jsonResult = {};

  for (let child of children) {
    let childIsArray =
      children.filter((eachChild) => eachChild.nodeName === child.nodeName).length > 1;

    if (childIsArray) {
      if (jsonResult[child.nodeName] === undefined) {
        jsonResult[child.nodeName] = [xml2json(child)];
      } else {
        jsonResult[child.nodeName].push(xml2json(child));
      }
    } else {
      jsonResult[child.nodeName] = xml2json(child);
    }
  }
  return jsonResult;
}
